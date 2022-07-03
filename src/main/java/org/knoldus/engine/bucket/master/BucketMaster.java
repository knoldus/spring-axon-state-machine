package org.knoldus.engine.bucket.master;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.knoldus.engine.bucket.command.CreateBucketCommand;
import org.knoldus.engine.bucket.dto.BucketMasterSyn;
import org.knoldus.engine.bucket.event.BucketCreated;
import org.knoldus.engine.bucket.master.flow.WriteBucketCreated;

import java.time.Instant;

@Aggregate
@Slf4j
public class BucketMaster {

    @AggregateIdentifier
    private String id;

    BucketMaster() {}

    @CommandHandler
    public BucketMaster(CreateBucketCommand createBucketCommand) {
        log.info("CreateBucketCommand received to create bucket - {}",
                createBucketCommand.getBucket().getBucketId());
        AggregateLifecycle.apply(new BucketCreated(createBucketCommand.getId(),
                createBucketCommand.getBucket()));
    }

    @EventSourcingHandler
    public void on(BucketCreated bucketCreated) throws JsonProcessingException {
        log.info("bucketCreated event occurred for bucket - {}",
                bucketCreated.getBucket().getBucketId());
        this.id = bucketCreated.getId();
        BucketMasterSyn bucketMasterSyn =
                new BucketMasterSyn(false, false, bucketCreated.getBucket().getBucketId(),
                Instant.now().toEpochMilli());
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String bucketMasterSynMessage = ow.writeValueAsString(bucketMasterSyn);
        WriteBucketCreated.send("master_buckets", bucketMasterSynMessage);
    }
}
