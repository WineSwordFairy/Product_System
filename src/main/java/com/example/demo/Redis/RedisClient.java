package com.example.demo.Redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 就一个小Demo 随便写下 * @author bigsea *
 */
@Component
public class RedisClient {
    @Autowired
    private JedisPool jedisPool;

    public void set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            ///获取一个连接吗？
            jedis = jedisPool.getResource();
            ///设置键？
            jedis.set(key, value);
        } finally {
            //返还到连接池
            jedis.close();
        }
    }

    public String get(String key) throws Exception {
        Jedis jedis = null;
        try {

            //拿一个连接。
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {            //返还到连接池
            jedis.close();
        }
    }
}

