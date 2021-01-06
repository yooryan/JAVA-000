package com.github.yooryan;

import com.github.yooryan.lock.JedisUtil;
import redis.clients.jedis.Jedis;

/**
 * @author linyunrui
 */
public class RedisCounter {


    /**
     * 减库存
     * @param key
     * @return
     */
    public long stock(String key){
        try (Jedis jedis = JedisUtil.getRerouse()){
            //预先减库存
            Long decr = jedis.decr(key);
            if (decr >= 0){
                //已经减库存,返回剩余库存
                return decr;
            }else {
                //库存不足
                return -1;
            }
        }
    }

    public static void main(String[] args) {
        final RedisCounter redisCounter = new RedisCounter();
        final long s1 = redisCounter.stock("s1");
        System.out.println(s1);
    }



}
