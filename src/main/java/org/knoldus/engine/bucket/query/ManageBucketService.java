package org.knoldus.engine.bucket.query;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.knoldus.engine.bucket.event.EligibleTradeEvent;
import org.knoldus.engine.bucket.event.ToldBucketCutOff;
import org.knoldus.engine.bucket.machine.BucketState;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ManageBucketService {

    private final BucketRepository bucketRepository;


    public ManageBucketService(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    @EventHandler
    public void on(EligibleTradeEvent eligibleTradeEvent) {
        log.info("Handling EligibleTradeEvent...");
        BucketEntity bucket = new BucketEntity();
        bucket.setBucketId(eligibleTradeEvent.getTradeAd().getBucket().getUid());
        bucket.setAllocationAmount(eligibleTradeEvent.getTradeAd().getAllocationAmount());
        bucket.setClassificationId(eligibleTradeEvent.getTradeAd().getClassificationId());
        bucket.setDirection(eligibleTradeEvent.getTradeAd().getDirection());
        bucket.setTradeId(eligibleTradeEvent.getTradeAd().getTradeId());
        bucket.setQuantity(eligibleTradeEvent.getTradeAd().getQuantity());
        bucket.setConfirmationAmount(eligibleTradeEvent.getTradeAd().getConfirmationAmount());
        bucket.setBucketState(BucketState.OPEN);

        bucketRepository.save(bucket);
    }

//    @EventHandler
//    public void on(ToldBucketCutOff toldBucketCutOff) {
//      //TODO
//    }
}
