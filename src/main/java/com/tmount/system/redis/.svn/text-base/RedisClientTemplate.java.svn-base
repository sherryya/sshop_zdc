package com.tmount.system.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisClientTemplate {
	/**         
     * 数据源        
     */        
    private JedisPool jedisPool;   
       
    /**        
     * 获取数据库连接         
     * @return conn         
     */        
    public Jedis getConnection() {   
        Jedis jedis=null;             
        try {                 
            jedis=jedisPool.getResource();             
        } catch (Exception e) {                 
            e.printStackTrace();             
        }             
        return jedis;         
    }      
       
    /**         
     * 关闭数据库连接         
     * @param conn         
     */        
    public void closeConnection(Jedis jedis) {             
        if (null != jedis) {                 
            try {                     
                jedisPool.returnResource(jedis);                 
            } catch (Exception e) {
            	jedisPool.returnBrokenResource(jedis);
            	e.printStackTrace();
            }             
        }         
    }     
       
    /**         
     * 设置连接池         
     * @param 数据源        
     */        
    public void setJedisPool(JedisPool JedisPool) {   
        this.jedisPool = JedisPool;         
    }          
       
    /**         
     * 获取连接池         
     * @return 数据源         
     */        
    public JedisPool getJedisPool() {   
        return jedisPool;         
    }    
}
