package com.github.yooryan.activemqdemo;


import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;

public class ConsumerImpl implements Consumer {
    Properties properties;
    private final String topic = "order-test";
    private KafkaConsumer<String, String> consumer;
    private Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
    private Set<String> orderSet = new HashSet<>();

    public ConsumerImpl() {
        properties = new Properties();
//        properties.put("enable.auto.commit", false);
//        properties.put("isolation.level", "read_committed");
//        properties.put("auto.offset.reset", "latest");
        properties.put("group.id", "java1-kimmking");
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer(properties);
    }

    @Override
    public void consumeOrder() {
        //订阅topic
        consumer.subscribe(Collections.singletonList(topic));
        while (true){
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofMillis(50));
            for (ConsumerRecord<String, String> record : poll) {
                System.out.println(record);

               // final Order order = JSON.parseObject(record.value(), Order.class);
                //deduplicationOrder(order);
                currentOffsets.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1, "no matadata"));
                consumer.commitAsync(currentOffsets, (offsets, exception) -> {
                    if (exception != null) {
                        exception.printStackTrace();
                    }
                });
            }
        }
    }

    @Override
    public void close() {

    }

    private void deduplicationOrder(Order order) {
        orderSet.add(order.getId().toString());
    }
}
