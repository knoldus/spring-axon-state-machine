package org.knoldus.engine.bucket.machine;

import org.knoldus.engine.bucket.command.MasterCutOffCommand;
import org.knoldus.engine.bucket.command.MasterLockCommand;
import org.knoldus.engine.bucket.command.TradeEligibleCommand;
import org.knoldus.engine.bucket.dto.TradeAd;
import org.knoldus.engine.bucket.event.EligibleTrade;
import org.knoldus.engine.bucket.event.MasterLocked;
import org.knoldus.engine.bucket.event.ToldBucketCutOff;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

@Aggregate
@Slf4j
public class BucketAggregate {

    @AggregateIdentifier
    private String bucketId;

    private BucketState bucketState;

    private List<TradeAd> bucketTrades = new ArrayList<>();

    private Double netTrade;
    BucketAggregate(){}

    @CommandHandler
    public BucketAggregate(TradeEligibleCommand tradeEligibleCommand) {
        this.bucketState = BucketState.INIT;
        log.info("TradeEligibleCommand received.");
        AggregateLifecycle.apply(new EligibleTrade(tradeEligibleCommand.getId(),
                tradeEligibleCommand.getTradeAd()));
    }

    @EventSourcingHandler
    public void on(EligibleTrade eligibleTrade) {
        log.info("An EligibleTradeEvent occurred.");
        this.bucketId = eligibleTrade.getTradeAd().getBucketId();
        this.bucketState = BucketState.OPEN;
        this.bucketTrades.add(eligibleTrade.getTradeAd());
    }

    @CommandHandler
    public void on(MasterCutOffCommand masterCutOffCommand) {
        log.info("MasterCutOffCommand received.");
        AggregateLifecycle.apply(new ToldBucketCutOff(masterCutOffCommand.getId(),
                masterCutOffCommand.getBucketMasterSyn()));
    }

    @EventSourcingHandler
    public void on(ToldBucketCutOff toldBucketCutOff) {
        log.info("An ToldBucketCutOff occurred.");
        this.bucketId = toldBucketCutOff.getBucketMasterSyn().getBucketId();
        this.bucketState = BucketState.CUTOFF;
        netTrade = bucketTrades.stream()
                .map(TradeAd::getAllocationAmount)
                .reduce(0.0, Double::sum);
    }

    @CommandHandler
    public void on(MasterLockCommand masterLock) {
        log.info("An ToldBucketCutOff occurred.");
        AggregateLifecycle.apply(new MasterLocked(masterLock.getId(),
                masterLock.getBucketMasterSyn()));
    }

    @EventSourcingHandler
    public void on(MasterLocked masterLocked) {
        log.info("An ToldBucketCutOff occurred.");
        this.bucketId = masterLocked.getBucketMasterSyn().getBucketId();
        this.bucketState = BucketState.LOCKED;
        System.out.println(netTrade);
    }

}
