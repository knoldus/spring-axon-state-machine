package engine.bucket.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import engine.ApplicationConstant;
import engine.bucket.command.MasterCutOffCommand;
import engine.bucket.command.TradeEligibleCommand;
import engine.bucket.dto.BucketMasterSyn;
import engine.bucket.dto.TradeAd;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class ReadMasterSyn {

    private final CommandGateway commandGateway;

    @KafkaListener(
            groupId = ApplicationConstant.GROUP_ID_JSON,
            topics = ApplicationConstant.TOPIC_NAME_BUCKET_MASTER,
            containerFactory = ApplicationConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
    public void receivedMessage(byte[] message) throws JsonProcessingException {

        String bucketMasterSynMessage = new String(message, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        BucketMasterSyn bucketMasterSyn =
                mapper.readValue(bucketMasterSynMessage, BucketMasterSyn.class);
        if (bucketMasterSyn.isCutoff()) {

            CompletableFuture<Object> send =
                    commandGateway.send(
                            new MasterCutOffCommand(bucketMasterSyn.getBucket().getUid(),
                                    bucketMasterSyn));

        }

    }
}
