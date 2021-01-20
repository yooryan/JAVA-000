package com.github.yooryan.core;

/**
 * @author linyunrui
 */

public class RyanProducer {

    private RyanBroker broker;

    public RyanProducer(RyanBroker broker) {
        this.broker = broker;
    }
    public boolean send(String topic, RyanMessage message) {
        Ryanmq ryanmq = this.broker.findmq(topic);
        if(null == ryanmq){
          //  throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
            broker.createTopic(topic);
            //find again
            ryanmq = this.broker.findmq(topic);
        }
        return ryanmq.send(message);
    }
}
