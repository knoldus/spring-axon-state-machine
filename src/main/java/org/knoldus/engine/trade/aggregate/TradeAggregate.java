package org.knoldus.engine.trade.aggregate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.knoldus.engine.bucket.dto.EligibleTradeData;
import org.knoldus.engine.trade.command.CreateTradeCommand;
import org.knoldus.engine.trade.dto.TradeData;
import org.knoldus.engine.trade.event.TradeCreated;
import org.knoldus.engine.trade.flow.WriteEligibleTrade;
import org.springframework.beans.factory.annotation.Autowired;

import static org.knoldus.engine.ApplicationConstant.TOPIC_NAME_BUCKET_TRADE;

@Aggregate
@Slf4j
public class TradeAggregate {

    @AggregateIdentifier
    private String tradeId;

    private TradeState tradeState;

    TradeAggregate(){}

    @CommandHandler
    public TradeAggregate(CreateTradeCommand createTradeCommand) {
        log.info("CreateTradeCommand received for tradeId - {}",
                createTradeCommand.getTradeData().getTradeId());
        this.tradeState = TradeState.INIT;
        this.tradeId = createTradeCommand.getTradeData().getTradeId();
        AggregateLifecycle.apply(new TradeCreated(createTradeCommand.getId(),
                createTradeCommand.getTradeData()));
    }

    @EventSourcingHandler
    public void on(TradeCreated tradeCreated) throws JsonProcessingException {
        log.info("An TradeCreated occurred for tradeId - {}",
                tradeCreated.getTradeData().getTradeId());
        this.tradeState = TradeState.PENDING;
        this.tradeId = tradeCreated.getTradeData().getTradeId();
        TradeData tradeData = tradeCreated.getTradeData();

        //send for classification
        // Trade classified to a group is eligible and assign the bucketId;
        // Then send the Eligible trade to bucket aggregate.

        String tradeBucket = null;
        if ("636711_6367113".equals(tradeData.getTradeId())) {
            tradeBucket = "bucket1";
        } else if ("536731_1367119".equals(tradeData.getTradeId())) {
            tradeBucket = "bucket1";
        } else if ("236719_7367113".equals(tradeData.getTradeId())) {
            tradeBucket = "bucket1";
        } else if ("936711_2365113".equals(tradeData.getTradeId())) {
            tradeBucket = "bucket2";
        } else if ("834711_9367173".equals(tradeData.getTradeId())) {
            tradeBucket = "bucket2";
        }
        EligibleTradeData eligibleTrade = new EligibleTradeData(tradeData.getTradeId(), true, tradeData.getDirection(),
                tradeData.getQuantity(), tradeData.getAllocationAmount(),
                tradeData.getConfirmationAmount(), tradeData.getEb(), tradeData.getPb(), tradeBucket);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String eligibleTradeMessage = ow.writeValueAsString(eligibleTrade);
        WriteEligibleTrade.send("trades_buckets", eligibleTradeMessage);
    }

}
