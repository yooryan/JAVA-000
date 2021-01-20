package com.github.yooryan.core;

/**
 * @author linyunrui
 */
public class RyanConsumer<T> {
    private final RyanBroker broker;

    private Ryanmq ryanmq;

    public RyanConsumer(RyanBroker broker) {
        this.broker = broker;
    }

    public void subscribe(String topic){
        this.ryanmq = this.broker.findmq(topic);
    }

   /* public RyanMessage<T> poll(long timeout) {
        try {
            final RyanMessage poll = ryanmq.poll(timeout);
            offset(1);
            return poll;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public RyanMessage<T> poll()  {
        final RyanMessage poll = ryanmq.poll();
        if (poll != null){
            offset(1);
        }
        return poll;
    }

    public int offset(int offset){
        return ryanmq.offset(offset);
    }
}
