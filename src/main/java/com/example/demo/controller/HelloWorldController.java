package com.example.demo.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.util.HashMap;

@RestController
@ResponseBody
@Slf4j
public class HelloWorldController {


    @RequestMapping("/hello")
    public String hello() {
        return "remote hello";
    }

    @RequestMapping(value = "/remote")
    public String remote(){
        return "remote";
    }

    @RequestMapping(value = "/test")
    public String test(){
        return "remote";
    }

    @RequestMapping("/jrebel")
    public Integer jrebel() {
        int end = 1000;
        for (int i = 0; i < end; i++) {
            System.out.println(i);
        }

        return Integer.valueOf(end);
    }

    @RequestMapping(value = "sec")
    private void SignUtils() {
        String secret = "sfE8fLnW6tpJkopQ3fsFj2lwgnvUbj";
        String platformId = "xgxt";
        String subSystemId = "kkjh";
        String uniqueId = "123456789";

        String url = "http://ywtb.yczbfx.com//api/applyevent/message";

        String s = md5(md5(platformId + subSystemId + uniqueId, "", false) + secret, "", false);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("source","xgxt");
        paramMap.put("sign",s);
        paramMap.put("type","kkjh");
        paramMap.put("onlykey","123456789");
        paramMap.put("receiver_userid","yuangonghao");
        paramMap.put("title","展示标题");
        paramMap.put("content","XXX同学您好！您在研究生管理系统中提交的答辩，结果为答辩通过。");
        paramMap.put("makeday",1653962754);

        String result1 = HttpUtil.post(url, paramMap);
        System.out.println(result1);
    }


    public static String md5(String source, String salt, boolean toUpperCase) {
        source = source + salt;

        StringBuilder sb = new StringBuilder(32);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(source.getBytes("utf-8"));

            for (int i = 0; i < array.length; i++) {
                if(toUpperCase) {
                    sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
                } else {
                    sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
                }
            }
        } catch (Exception e) {
            log.error("Can not encode the string '" + source + "' to MD5!", e);
            throw new RuntimeException(e);
        }

        return sb.toString();
    }


}
