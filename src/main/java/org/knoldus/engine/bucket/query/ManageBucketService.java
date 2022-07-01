package org.knoldus.engine.bucket.query;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.knoldus.engine.bucket.event.EligibleTrade;
import org.knoldus.engine.bucket.event.MasterLocked;
import org.knoldus.engine.bucket.event.ToldBucketCutOff;
import org.knoldus.engine.bucket.machine.BucketState;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ManageBucketService {

    private final BucketRepository bucketRepository;
    BucketEntity bucket = new BucketEntity();


    public ManageBucketService(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    @EventHandler
    public void on(EligibleTrade eligibleTrade) {
        log.info("Handling EligibleTradeEvent...");
        bucket.setBucketId(eligibleTrade.getTradeAd().getBucketId());
        bucket.setBucketState(BucketState.OPEN);

        bucketRepository.save(bucket);

    }

    @EventHandler
    public void on(ToldBucketCutOff toldBucketCutOff) {
        log.info("Handling EligibleTradeEvent...");
        bucket.setBucketId(toldBucketCutOff.getBucketMasterSyn().getBucketId());
        bucket.setBucketState(BucketState.CUTOFF);
        bucketRepository.save(bucket);
    }

    @EventHandler
    public void on(MasterLocked masterLocked) {
        log.info("Handling EligibleTradeEvent...");
        bucket.setBucketId(masterLocked.getBucketMasterSyn().getBucketId());
        bucket.setBucketState(BucketState.LOCKED);
        bucketRepository.save(bucket);
    }

}
