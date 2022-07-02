package org.knoldus.engine;

public class ApplicationConstant {

    public static final String TOPIC_NAME_BUCKET_TRADE = "#{'${spring.kafka.consumer.bucket.topic}'}";
    public static final String TOPIC_NAME_TRADE_FEED= "#{'${spring.kafka.consumer.feed.topic}'}";
    public static final String TOPIC_NAME_CLASS_MASTER= "#{'${spring.kafka.consumer.classmaster.topic}'}";

    public static final String TOPIC_NAME_BUCKET_MASTER = "#{'${spring.kafka.consumer.bucketmaster.topic}'}";
    public static final String KAFKA_LISTENER_CONTAINER_FACTORY = "kafkaListenerContainerFactory";
    public static final String GROUP_ID_JSON = "group-id-json-3";

    public static final String TRUSTED_PACKAGE = "engine.bucket.dto";
}
