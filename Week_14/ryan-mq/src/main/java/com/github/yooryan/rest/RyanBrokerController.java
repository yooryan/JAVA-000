package com.github.yooryan.rest;

import com.github.yooryan.core.*;
import com.sun.javafx.image.ByteToIntPixelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author linyunrui
 */
@RestController
public class RyanBrokerController {

    @Autowired
    private RyanBroker broker;
    private Map<String,RyanConsumer> ryanConsumerMap = new ConcurrentHashMap<>(64);
    private Map<String,RyanProducer> ryanProducerMap = new ConcurrentHashMap<>(64);


    @PostMapping("createTopic")
    public boolean createTopic(String topic){
        broker.createTopic(topic);
        return true;
    }

    @PostMapping("send")
    public boolean send(@RequestParam String topic, @RequestBody RyanMessage message) {
        RyanProducer ryanProducer = ryanProducerMap.get(topic);
        if (ryanProducer == null){
            ryanProducer = broker.createProducer();
            ryanProducerMap.putIfAbsent(topic,ryanProducer);
        }
        return ryanProducer.send(topic,message);
    }

    @GetMapping("poll")
    public RyanMessage poll(String topic) {
        RyanConsumer ryanConsumer = ryanConsumerMap.get(topic);
        if (ryanConsumer == null){
            ryanConsumer = broker.createConsumer();
            ryanConsumerMap.putIfAbsent(topic,ryanConsumer);
        }
        ryanConsumer.subscribe(topic);
        return ryanConsumer.poll();
    }

}
