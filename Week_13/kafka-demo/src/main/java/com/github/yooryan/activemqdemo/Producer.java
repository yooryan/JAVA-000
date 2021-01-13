package com.github.yooryan.activemqdemo;


public interface Producer {

    void send(Order order);

    void close();

    // add your interface method here

    // and then implement it

}
