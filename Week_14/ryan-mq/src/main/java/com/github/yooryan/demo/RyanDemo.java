package com.github.yooryan.demo;

import com.github.yooryan.core.RyanBroker;
import com.github.yooryan.core.RyanConsumer;
import com.github.yooryan.core.RyanMessage;
import com.github.yooryan.core.RyanProducer;

import java.io.IOException;

/**
 * @author linyunrui
 */
public class RyanDemo {

    public static void main(String[] args) throws IOException {
        String topic = "test";
        final RyanBroker ryanBroker = new RyanBroker();
        final RyanConsumer consumer = ryanBroker.createConsumer();
        final RyanProducer producer = ryanBroker.createProducer();

        ryanBroker.createTopic(topic);
        consumer.subscribe(topic);
        final boolean[] flag = new boolean[1];
        flag[0] = true;
        new Thread(() ->{
            while (flag[0]){
                final RyanMessage poll = consumer.poll();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (poll != null){
                    System.out.println(poll.getBody());
                }
            }
        }).start();

        for (int i = 0; i < 1000; i++) {
            Order order = new Order(1000L + i, System.currentTimeMillis(), "USD2CNY", 6.51d);
            producer.send(topic, new RyanMessage(null, order));
        }

        while (true) {
            char c = (char) System.in.read();
            if(c > 20) {
                System.out.println(c);
                producer.send(topic, new RyanMessage(null, new Order(100000L + c, System.currentTimeMillis(), "USD2CNY", 6.52d)));
            }

            if( c == 'q' || c == 'e') break;
        }
        flag[0] = false;

    }
}
