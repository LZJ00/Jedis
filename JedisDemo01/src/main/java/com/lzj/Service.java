package com.lzj;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

/**
 * @ClassName Service
 * @Description: TODO
 * @Author:
 **/
public class Service {
    private String id; // 用户级别
    private int num; // 操作次数

    public Service() {
    }

    public Service(String id, int num) {
        this.id = id;
        this.num = num;
    }

    public void service(){
        Jedis jedis = JedisUtils.getJedis();
        String value = jedis.get("USERID:" + id);
        // 判断这个 Key 是否存在
        try {
            if (null == value){
                jedis.setex("USERID:" + id, 10, String.valueOf(Long.MAX_VALUE - num));
            }else{
                // 如果不存在，先自增，再执行下载
                Long val = jedis.incr("USERID:" + id);
                download(id, num - (Long.MAX_VALUE - val));// val = Long.MAX_VALUE - num + 1
                // num - (num - 1)
            }
        }catch (JedisDataException e){
            System.out.println(Thread.currentThread().getName() + " >>> 下载次数到达上限，请升级会员级别！");
        }finally {
            jedis.close();
        }
    }

    private void download(String id, long val) {
        System.out.println("用户:" + id + " 下载行第" + val + "次");
    }
}
