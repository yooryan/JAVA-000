package com.github.yooryan.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author linyunrui
 */
public class  RyanBroker {

    public static final int CAPACITY = 10000;

    private final Map<String, Ryanmq> ryanmqMap = new ConcurrentHashMap<>(64);

    public void createTopic(String name){
        ryanmqMap.putIfAbsent(name, new Ryanmq(name,CAPACITY));
    }

    public Ryanmq findmq(String topic){
        return this.ryanmqMap.get(topic);
    }

    public RyanProducer createProducer() {
        return new RyanProducer(this);
    }

    public RyanConsumer createConsumer() {
        return new RyanConsumer(this);
    }


}
