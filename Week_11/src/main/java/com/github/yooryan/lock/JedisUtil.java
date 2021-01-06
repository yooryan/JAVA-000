package com.github.yooryan.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author linyunrui
 */
public class JedisUtil {

    private static JedisPool jedisPool;
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10000);
        jedisPoolConfig.setMaxWaitMillis(10000);
        jedisPoolConfig.setMaxIdle(1000);
        jedisPool = new JedisPool(jedisPoolConfig,"localhost",6379,100000);

    }

    public static Jedis getRerouse(){
        return  jedisPool.getResource();
    }

}
