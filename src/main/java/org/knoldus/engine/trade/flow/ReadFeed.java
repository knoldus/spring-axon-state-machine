package org.knoldus.engine.trade.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.knoldus.engine.ApplicationConstant;
import org.knoldus.engine.bucket.command.CreateBucketCommand;
import org.knoldus.engine.bucket.dto.Bucket;
import org.knoldus.engine.trade.command.CreateTradeCommand;
import org.knoldus.engine.trade.dto.TradeData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class ReadFeed {

    private final CommandGateway commandGateway;

    @KafkaListener(
            groupId = ApplicationConstant.GROUP_ID_JSON,
            topics = ApplicationConstant.TOPIC_NAME_TRADE_FEED,
            containerFactory = ApplicationConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
    public void receivedMessage(byte[] message) throws JsonProcessingException, ExecutionException, InterruptedException {

        String tradeMessage = new String(message, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        TradeData tradeData = mapper.readValue(tradeMessage, TradeData.class);

        commandGateway.send(
                new CreateTradeCommand(UUID.randomUUID().toString(), tradeData));

    }
}
