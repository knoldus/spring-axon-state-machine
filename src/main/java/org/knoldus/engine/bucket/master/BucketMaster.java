package org.knoldus.engine.bucket.master;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.knoldus.engine.bucket.command.CreateBucketCommand;
import org.knoldus.engine.bucket.event.BucketCreated;
import org.knoldus.engine.bucket.main.aggregate.BucketAggregate;

@Aggregate
@Slf4j
public class BucketMaster {

    @AggregateIdentifier
    private String id;

    BucketMaster() {}

    @CommandHandler
    public BucketMaster(CreateBucketCommand createBucketCommand) {
        log.info("CreateBucketCommand received");
        AggregateLifecycle.apply(new BucketCreated(createBucketCommand.getId(),
                createBucketCommand.getBucket()));
    }

    @EventSourcingHandler
    public void on(BucketCreated bucketCreated) {
        log.info("bucketCreated event occurred");
        this.id = bucketCreated.getBucket().getBucketId();
        BucketAggregate bucketAggregate = new BucketAggregate(bucketCreated);
        System.out.println(bucketCreated.getBucket().getBucketId());
    }
}
