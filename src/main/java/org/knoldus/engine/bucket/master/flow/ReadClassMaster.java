package org.knoldus.engine.bucket.master.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.knoldus.engine.ApplicationConstant;
import org.knoldus.engine.bucket.command.CreateBucketCommand;
import org.knoldus.engine.bucket.dto.Bucket;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class ReadClassMaster {

    private final CommandGateway commandGateway;

    @KafkaListener(
            groupId = ApplicationConstant.GROUP_ID_JSON,
            topics = ApplicationConstant.TOPIC_NAME_CLASS_MASTER,
            containerFactory = ApplicationConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
    public void receivedMessage(byte[] message) throws JsonProcessingException {

        String bucketMessage = new String(message, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        Bucket bucket = mapper.readValue(bucketMessage, Bucket.class);

            CompletableFuture<Object> send =
                    commandGateway.send(
                            new CreateBucketCommand(UUID.randomUUID().toString(), bucket));

    }
}
