package com.lzj;

/**
 * @ClassName MyRun
 * @Description: TODO
 * @Author:
 **/
public class MyRun implements Runnable{
    private Service sc;

    /**
     *
     * @param id 用户级别
     * @param num 操作次数
     */
    public MyRun(String id, int num){
        sc = new Service(id, num);
    }

    public void run() {
        while (true){
            sc.service();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
