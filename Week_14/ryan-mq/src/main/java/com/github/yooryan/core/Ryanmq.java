package com.github.yooryan.core;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author linyunrui
 */
@Slf4j
@Data
public class Ryanmq {

    private String topic;
    private int capacity;
    private RyanMessage[] queue;
    private AtomicInteger length;
    private AtomicInteger offset;

    public Ryanmq(String topic, int capacity) {
        this.offset = new AtomicInteger(0);
        this.length = new AtomicInteger(0);
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new RyanMessage[capacity];
    }

    public boolean send(RyanMessage ryanMessage){
        boolean flag = true;
        try {
            queue[length.getAndIncrement()] = ryanMessage;
        } catch (Exception e) {
            log.error("Ryanmq#send {}",e);
            flag = false;
        }
        return flag;
    }

    public RyanMessage poll() {
        return queue[this.offset.get()];
    }

    public RyanMessage poll(int offset) {
        return queue[this.offset.get()];
    }

    public int offset(int offset){
        return this.offset.addAndGet(offset);
    }
}
