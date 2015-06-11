package com.tmount.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisClientTemplate {    
    private JedisPool jedisPool;
    
    public Jedis getConnection(){
    	Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
        } catch (Exception e) {
            //e.printStackTrace();
        } 
        return jedis;
    }
    public void closeConnection(Jedis jedis) {
        if (null != jedis) {
            try {
                jedisPool.returnResource(jedis);
            } catch (Exception e) {
                    //e.printStackTrace();
            }
        }
    }
    public void setJedisPool(JedisPool JedisPool) {
        this.jedisPool = JedisPool;
    }
    public JedisPool getJedisPool() {
        return jedisPool;
    }
}
