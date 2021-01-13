package com.github.yooryan;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author linyunrui
 */
@Component
public class KafkaConsumer {

        @KafkaListener(topics = {"spring-test"})
        public void listen(ConsumerRecord record){
            System.out.println(record.topic()+":"+record.value());
        }
}
