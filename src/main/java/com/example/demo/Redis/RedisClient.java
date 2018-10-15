package com.example.demo.Redis;

import com.example.demo.BaseClass.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * 就一个小Demo 随便写下 * @author bigsea *
 */
@Component
public class RedisClient extends Person {
    @Autowired
    private JedisPool jedisPool;


    public Jedis GetResource(String... keys) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception ex) {
            return null;
        }
            return jedis;
    }


    ///检查某个键是否存在。
    public boolean CheckIsKeyExists(String key) {
        Jedis jedis = null;
        try {
            ///获取一个连接吗？
            jedis = jedisPool.getResource();
            ///设置键？
            boolean isExists = jedis.exists(key);

            return isExists;
        } finally {
            //返还到连接池
            jedis.close();
        }
    }

    public void set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            ///获取一个连接吗？
            jedis = jedisPool.getResource();
            ///设置键？
            jedis.set(key, value);

            //test
            Transaction tran = jedis.multi();
            Response<Long> r = tran.incrBy("", -1);
            List<Object> res = tran.exec();
        } finally {
            //返还到连接池
            jedis.close();
        }
    }


        //获取键值.
        public String get (String key) throws Exception {
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

