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

}
