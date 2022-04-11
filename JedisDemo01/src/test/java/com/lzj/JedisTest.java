package com.lzj;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @ClassName JedisTest
 * @Description: TODO Jedis 快速上手
 * @Author:
 **/
public class JedisTest {
    @Test
    public void test01(){
        // 连接 Redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        // 设置数据
        jedis.set("infoFromJava", "Jedis");

        // 获取数据
        String infoFromJava = jedis.get("infoFromJava");

        System.out.println("infoFromJava = " + infoFromJava);

        // 关闭 Redis的连接
        jedis.close();
    }
    @Test
    public void test02(){
        // 连接 Redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        Long rpush = jedis.rpush("nameList", "Andy", "Jack", "Lily", "Tom");
        System.out.println("rpush = " + rpush);

        List<String> nameList = jedis.lrange("nameList", 0, -1);
        for (String name : nameList) {
            System.out.println(name);
        }

        // 关闭 Redis的连接
        jedis.close();
    }
}
