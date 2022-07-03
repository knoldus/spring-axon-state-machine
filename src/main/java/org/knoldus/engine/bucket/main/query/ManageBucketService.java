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
import java.util.Optional;

@Service
@Slf4j
public class ManageBucketService {

    private final BucketRepository bucketRepository;
    BucketEntity bucket = new BucketEntity();

    public ManageBucketService(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    @EventHandler
    public void on(BucketCreated bucketCreated) {
        log.info("Handling BucketCreated event for bucketId {}",
                bucketCreated.getBucket().getBucketId());
        List<String> tradeList = new ArrayList<>();
        bucket.setBucketId(bucketCreated.getBucket().getBucketId());
        bucket.setTradeIds(tradeList);
        bucket.setBucketState(BucketState.INIT);
        bucketRepository.save(bucket);
    }

    @EventHandler
    public void on(EligibleTrade eligibleTrade) {
        log.info("Handling EligibleTrade event for bucketId - {}",
                eligibleTrade.getTradeAd().getBucketId());
        Optional<BucketEntity> byId =
                bucketRepository.findById(eligibleTrade.getTradeAd().getBucketId());
        List<String> tradeIds = byId.get().getTradeIds();
        tradeIds.add(eligibleTrade.getTradeAd().getTradeId());
        bucket.setBucketId(eligibleTrade.getTradeAd().getBucketId());
        bucket.setTradeIds(tradeIds);
        bucket.setBucketState(BucketState.OPEN);
        bucketRepository.save(bucket);
    }

    @EventHandler
    public void on(ToldBucketCutOff toldBucketCutOff) {
        log.info("Handling toldBucketCutOff event for bucketId - {}",
                toldBucketCutOff.getBucketMasterSyn().getBucketId());
        Optional<BucketEntity> byId =
                bucketRepository.findById(toldBucketCutOff.getBucketMasterSyn().getBucketId());
        List<String> tradeIds = byId.get().getTradeIds();
        bucket.setBucketId(toldBucketCutOff.getBucketMasterSyn().getBucketId());
        bucket.setTradeIds(tradeIds);
        bucket.setBucketState(BucketState.CUTOFF);
        bucketRepository.save(bucket);
    }

    @EventHandler
    public void on(MasterLocked masterLocked) {
        log.info("Handling MasterLocked event for bucketId - {}",
                masterLocked.getBucketMasterSyn().getBucketId());
        Optional<BucketEntity> byId =
                bucketRepository.findById(masterLocked.getBucketMasterSyn().getBucketId());
        List<String> tradeIds = byId.get().getTradeIds();
        bucket.setBucketId(masterLocked.getBucketMasterSyn().getBucketId());
        bucket.setTradeIds(tradeIds);
        bucket.setBucketState(BucketState.LOCKED);
        bucketRepository.save(bucket);
    }

}
