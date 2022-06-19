package com.example.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.cache.selector.SimpleReadCacheSelector;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.example.demo.domain.BankItem;
import com.example.demo.listener.DemoDataListener;
import com.example.demo.util.TestFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.concurrent.*;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void simpleRead(){
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "bankitem.xlsx";
        String fileName = "D:\\WeChat Files\\wxid_rpsjrglon7ea21\\FileStorage\\MsgAttach\\5c18e4f52c9be12f5e5fe9009c0a5d5d\\File\\2022-06\\bankitem.xlsx";
        EasyExcel.read(fileName, BankItem.class, new PageReadListener<BankItem>(dataList -> {
            for (BankItem demoData : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }
        })).readCacheSelector(new SimpleReadCacheSelector(5, 20)).sheet().doRead();
    }


    @Test
    public void readAll(){
        String fileName = "D:\\WeChat Files\\wxid_rpsjrglon7ea21\\FileStorage\\MsgAttach\\5c18e4f52c9be12f5e5fe9009c0a5d5d\\File\\2022-06\\bankitem.xlsx";
        EasyExcel.read(fileName, BankItem.class, new DemoDataListener()).doReadAll();
    }


    @Test
    public void fixedThreadPool(){
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("当前线程:"+Thread.currentThread().getName());

            }
        };

        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
    }

    @Test
    public void fixedThreadPool1(){
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(() -> {
            System.out.println("当前线程:"+Thread.currentThread().getName());
        });

        threadPool.execute(() -> {
            System.out.println("当前线程:"+Thread.currentThread().getName());
        });
        threadPool.execute(() -> {
            System.out.println("当前线程:"+Thread.currentThread().getName());
        });
        threadPool.execute(() -> {
            System.out.println("当前线程:"+Thread.currentThread().getName());
        });
        threadPool.execute(() -> {
            System.out.println("当前线程:"+Thread.currentThread().getName());
        });
    }


    @Test
    public void cachedThreadPool(){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                System.out.println("当前线程" + Thread.currentThread().getName());
                try{
                    TimeUnit.SECONDS.sleep(1);

                }catch (InterruptedException e){

                }
            });
        }
    }

    @Test
    public void singleThreadPool(){
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + "任务被执行");
            });
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){

            }
        }

    }


    /**
     * 推荐的线程创建方式
     */
    @Test
    public void myThreadPoolExecutor(){
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));

        for(int i = 0; i< 10; i++){
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + "被执行，线程名称为：" + Thread.currentThread().getName());
            });

            try{
                Thread.sleep(1000);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


}
