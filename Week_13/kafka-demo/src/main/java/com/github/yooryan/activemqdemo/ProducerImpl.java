package com.github.yooryan.activemqdemo;


import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerImpl implements Producer {

    Properties properties;
    private final String topic = "order-test";
    private KafkaProducer<String, String> producer;
    public ProducerImpl() {
        properties = new Properties();
//        properties.put("enable.auto.commit", false);
//        properties.put("isolation.level", "read_committed");
//        properties.put("auto.offset.reset", "latest");
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String,String>(properties);
    }

    @Override
    public void send(Order order) {
        ProducerRecord record = new ProducerRecord(topic, order.getId().toString(), JSON.toJSONString(order));
        producer.send(record, (metadata, exception) -> {
        });
    }

    @Override
    public void close() {

    }
}
