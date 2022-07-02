package org.knoldus.engine.bucket.main.query;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.knoldus.engine.bucket.event.BucketCreated;
import org.knoldus.engine.bucket.event.EligibleTrade;
import org.knoldus.engine.bucket.event.MasterLocked;
import org.knoldus.engine.bucket.event.ToldBucketCutOff;
import org.knoldus.engine.bucket.main.aggregate.BucketState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ManageBucketService {

    private final BucketRepository bucketRepository;
    private List<String> tradeList = new ArrayList<>();
    BucketEntity bucket = new BucketEntity();

    public ManageBucketService(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    @EventHandler
    public void on(BucketCreated bucketCreated) {
        log.info("Handling BucketCreated...");
        bucket.setBucketId(bucketCreated.getBucket().getBucketId());
        bucket.setTradeId(tradeList);
        bucket.setBucketState(BucketState.INIT);
        bucketRepository.save(bucket);
    }

//    @EventHandler
//    public void on(EligibleTrade eligibleTrade) {
//        log.info("Handling EligibleTrade...");
//        tradeList.add(eligibleTrade.getTradeAd().getTradeId());
//        bucket.setBucketId(eligibleTrade.getTradeAd().getBucketId());
//        bucket.setTradeId(tradeList);
//        bucket.setBucketState(BucketState.OPEN);
//        bucketRepository.save(bucket);
//    }

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
