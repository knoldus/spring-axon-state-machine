package org.knoldus.engine.bucket.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.knoldus.engine.ApplicationConstant;
import org.knoldus.engine.bucket.command.TradeEligibleCommand;
import org.knoldus.engine.bucket.dto.TradeAd;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class ReadBucketTrade {

    private final CommandGateway commandGateway;

    @KafkaListener(
            groupId = ApplicationConstant.GROUP_ID_JSON,
            topics = ApplicationConstant.TOPIC_NAME_BUCKET_TRADE,
            containerFactory = ApplicationConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
    public void receivedMessage(byte[] message) throws JsonProcessingException, ExecutionException, InterruptedException {

        String tradeAdMessage = new String(message, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        TradeAd tradeAd = mapper.readValue(tradeAdMessage, TradeAd.class);
        if (tradeAd.isTradeEligible()) {

            CompletableFuture<Object> send =
                    commandGateway.send(
                            new TradeEligibleCommand(UUID.randomUUID().toString(), tradeAd));
            Object o = send.get();
            System.out.println(o);

        }

    }
}
