package engine.bucket.machine;

import engine.bucket.command.MasterCutOffCommand;
import engine.bucket.command.TradeEligibleCommand;
import engine.bucket.dto.TradeAd;
import engine.bucket.event.EligibleTradeEvent;
import engine.bucket.event.ToldBucketCutOff;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class BucketAggregate {

    @AggregateIdentifier
    private String bucketId;

    private BucketState bucketState;
    BucketAggregate(){}

    @CommandHandler
    public BucketAggregate(TradeEligibleCommand tradeEligibleCommand) {
        this.bucketState = BucketState.INIT;
        log.info("TradeEligibleCommand received.");
        AggregateLifecycle.apply(new EligibleTradeEvent(tradeEligibleCommand.getId(),
                tradeEligibleCommand.getTradeAd()));
    }

    @EventSourcingHandler
    public void on(EligibleTradeEvent eligibleTradeEvent) {
        log.info("An EligibleTradeEvent occurred.");
        this.bucketId = eligibleTradeEvent.getTradeAd().getBucket().getUid();
        this.bucketState = BucketState.OPEN;
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
        this.bucketId = toldBucketCutOff.getBucketMasterSyn().getBucket().getUid();
        this.bucketState = BucketState.CUTOFF;
    }


}
