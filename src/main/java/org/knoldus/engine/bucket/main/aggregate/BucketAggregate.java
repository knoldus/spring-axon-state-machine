package org.knoldus.engine.bucket.main.aggregate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.knoldus.engine.bucket.command.MasterCutOffCommand;
import org.knoldus.engine.bucket.command.MasterLockCommand;
import org.knoldus.engine.bucket.command.PlaceBucketCommand;
import org.knoldus.engine.bucket.command.TradeEligibleCommand;
import org.knoldus.engine.bucket.dto.EligibleTradeData;
import org.knoldus.engine.bucket.dto.NettedTrade;
import org.knoldus.engine.bucket.event.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.knoldus.engine.bucket.main.flow.WriteNettedTrade;

import java.util.ArrayList;
import java.util.List;

@Aggregate
@Slf4j
public class BucketAggregate {

    @AggregateIdentifier
    private String bucketId;

    private BucketState bucketState;

    private List<EligibleTradeData> bucketTrades;

    private Double netTrade;
    BucketAggregate(){}

    @CommandHandler
    public BucketAggregate(PlaceBucketCommand placeBucketCommand) {

        log.info("PlaceBucketCommand command received for bucketId - {}.",
                placeBucketCommand.getBucketMasterSyn().getBucketId());
        AggregateLifecycle.apply(new BucketPlaced(placeBucketCommand.getId(),
                placeBucketCommand.getBucketMasterSyn()));
    }

    @EventSourcingHandler
    public void on(BucketPlaced bucketPlaced) {

        log.info("An BucketPlaced event occurred for bucketId - {}.",
                bucketPlaced.getBucketMasterSyn().getBucketId());
        this.bucketId = bucketPlaced.getBucketMasterSyn().getBucketId();
        this.bucketTrades = new ArrayList<>();
        this.bucketState = BucketState.INIT;
    }

    @CommandHandler
    public void handle(TradeEligibleCommand tradeEligibleCommand) {

        this.bucketId = tradeEligibleCommand.getId();
        log.info("TradeEligibleCommand received for bucketId - {}.",
                tradeEligibleCommand.getTradeAd().getBucketId());
        AggregateLifecycle.apply(new EligibleTrade(tradeEligibleCommand.getId(),
                tradeEligibleCommand.getTradeAd()));
    }

    @EventSourcingHandler
    public void on(EligibleTrade eligibleTrade) {

        log.info("An EligibleTradeEvent occurred for bucketId - {}.",
                eligibleTrade.getTradeAd().getBucketId());
        this.bucketId = eligibleTrade.getTradeAd().getBucketId();
        this.bucketState = BucketState.OPEN;
        this.bucketTrades.add(eligibleTrade.getTradeAd());
    }

    @CommandHandler
    public void on(MasterCutOffCommand masterCutOffCommand) {

        log.info("MasterCutOffCommand received for bucketId - {}.",
                masterCutOffCommand.getBucketMasterSyn().getBucketId());
        AggregateLifecycle.apply(new ToldBucketCutOff(masterCutOffCommand.getId(),
                masterCutOffCommand.getBucketMasterSyn()));
    }

    @EventSourcingHandler
    public void on(ToldBucketCutOff toldBucketCutOff) {

        log.info("An ToldBucketCutOff event occurred for bucketId - {}.",
                toldBucketCutOff.getBucketMasterSyn().getBucketId());
        this.bucketId = toldBucketCutOff.getBucketMasterSyn().getBucketId();
        this.bucketState = BucketState.CUTOFF;
        netTrade = bucketTrades.stream()
                .map(EligibleTradeData::getAllocationAmount)
                .reduce(0.0, Double::sum);
    }

    @CommandHandler
    public void on(MasterLockCommand masterLock) {
        log.info("An MasterLockCommand received for bucketId - {}.",
                masterLock.getBucketMasterSyn().getBucketId());
        AggregateLifecycle.apply(new MasterLocked(masterLock.getId(),
                masterLock.getBucketMasterSyn()));
    }

    @EventSourcingHandler
    public void on(MasterLocked masterLocked) throws JsonProcessingException {

        log.info("An MasterLocked occurred for bucketId - {}.",
                masterLocked.getBucketMasterSyn().getBucketId());
        this.bucketId = masterLocked.getBucketMasterSyn().getBucketId();
        this.bucketState = BucketState.LOCKED;
        NettedTrade nettedTrade;
        if ("bucket1".equals(masterLocked.getBucketMasterSyn().getBucketId())) {
            nettedTrade = new NettedTrade("BUY", 9, netTrade, bucketTrades);
        } else  {
            nettedTrade = new NettedTrade("BUY", 6, netTrade, bucketTrades);
        }
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String netTradeMessage = ow.writeValueAsString(nettedTrade);
        System.out.println(netTradeMessage);
        WriteNettedTrade.send("net_trade", netTradeMessage);
    }

}
