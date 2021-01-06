package com.github.yooryan.lock;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @author linyunrui
 */
public class JedisLock {

    private static final String SUCCESS ="OK";
    private static final String SET_IF_NOT_EXIST ="NX";
    private static final String SET_WITH_EXPIRE_TIME ="EX";
    //释放锁成功标示
    private static final Long RELEASE_SUCCESS = 1L;
    //默认过期时间
    public static final int DEFAULT_EXPIRE_1000_MILLISECONDS = 1000;

    /**
     * 获取锁
     * @param key
     * @param value
     * @param expireTime
     * @param waitTime 尝试获取锁等待时间
     */
    public static void lock(String key,String value,int expireTime,long waitTime){
        try (Jedis jedis = JedisUtil.getRerouse()){
            while(!tryGetLock(jedis,key,value,expireTime)){
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 获取锁
     * @param key
     * @param value
     * @param expireTime
     */
    public static void lock(String key,String value,int expireTime){
        lock(key,value,expireTime,50);
    }

    public static boolean tryLock(String key,String value,int expireTime){
        try (Jedis jedis = JedisUtil.getRerouse()){
            return tryGetLock(jedis,key,value,expireTime);
        }
    }

    public static void unlock(String key,String value){
        try (Jedis jedis = JedisUtil.getRerouse()){
            releaseLock(jedis,key,value);
        }
    }


    private static Boolean tryGetLock(Jedis jedis, String key, String requestId, int expireTime){
        String result = jedis.set(key,requestId,SET_IF_NOT_EXIST,SET_WITH_EXPIRE_TIME,expireTime);
        if(SUCCESS.equals(result)){
            return true;
        }
        return false;
    }

    private static Boolean releaseLock(Jedis jedis,String key,String requestId){
        //避免释放其他线程持有的分布式锁
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(key),Collections.singletonList(requestId));
        if(RELEASE_SUCCESS.equals(result)){
            return true;
        }
        return false;
    }
}
