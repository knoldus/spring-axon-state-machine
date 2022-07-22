package org.knoldus.engine.bucket.main.flow;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.knoldus.engine.ApplicationConstant;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WriteNettedTrade {


    public static void send(String topicName, String message) {

        Map<String, Object> senderProps = new HashMap<>();
        senderProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        senderProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        senderProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        senderProps.put(JsonDeserializer.TRUSTED_PACKAGES, ApplicationConstant.TRUSTED_PACKAGE);
        DefaultKafkaProducerFactory<String, String> pf = new DefaultKafkaProducerFactory<>(senderProps);
        KafkaTemplate<String, String> template = new KafkaTemplate<>(pf);

       ListenableFuture<SendResult<String, String>> future =
                template.send(topicName, message);


        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
