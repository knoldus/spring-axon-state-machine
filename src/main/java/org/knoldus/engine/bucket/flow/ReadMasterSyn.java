package org.knoldus.engine.bucket.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.knoldus.engine.ApplicationConstant;
import org.knoldus.engine.bucket.command.MasterCutOffCommand;
import org.knoldus.engine.bucket.dto.BucketMasterSyn;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class ReadMasterSyn {

    private final CommandGateway commandGateway;

    @KafkaListener(
            groupId = ApplicationConstant.GROUP_ID_JSON,
            topics = ApplicationConstant.TOPIC_NAME_BUCKET_MASTER,
            containerFactory = ApplicationConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
    public void receivedMessage(byte[] message) throws JsonProcessingException, ExecutionException, InterruptedException {

        String bucketMasterSynMessage = new String(message, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        BucketMasterSyn bucketMasterSyn =
                mapper.readValue(bucketMasterSynMessage, BucketMasterSyn.class);
        if (bucketMasterSyn.isCutoff()) {

            CompletableFuture<Object> send =
                    commandGateway.send(
                            new MasterCutOffCommand(bucketMasterSyn.getBucket().getUid(),
                                    bucketMasterSyn));
            System.out.println(send.get());

        }

    }
}
