package com.lzj;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * @ClassName JdbcUtils
 * @Description: TODO
 * @Author:
 **/
public class JedisUtils {
    private static String host = null; // 连接 IP 地址
    private static String port = null; // 端口号
    private static String maxTotal = null; // 最大连接数
    private static String maxIdle = null; // 最大活跃数

    private static JedisPool jedisPool = null;

    static {
        // 获取配置文件对象
        ResourceBundle resourceBundle = ResourceBundle.getBundle("redis");

        // 通过配置文件中的键获取值
        host = resourceBundle.getString("redis.host");
        port = resourceBundle.getString("redis.port");
        maxTotal = resourceBundle.getString("redis.maxTotal");
        maxIdle = resourceBundle.getString("redis.maxIdle");

        // 创建 Redis 配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        // 设置详细配置参数
        jedisPoolConfig.setMaxTotal(Integer.parseInt(maxTotal)); // 最大连接数
        jedisPoolConfig.setMaxIdle(Integer.parseInt(maxIdle)); // 最大活跃数

        // 创建连接
        jedisPool = new JedisPool(jedisPoolConfig, host, Integer.parseInt(port));
    }

    /**
     * 获取 Redis 连接
     * @return 获取到的连接
     */
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

}
