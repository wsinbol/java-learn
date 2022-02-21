package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.User;
import com.example.demo.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user") // @RequestMapping 默认映射所有HTTP Action，你可以使用@RequestMapping(method=ActionType)来缩小这个映射
public class UserController {

    @Autowired
    private UserServer userServer;


    // PathVariable example
    // @GetMapping(value = "/query/{name}") // PathVariable用于获取url中的参数，对应访问链接为：localhost:8080/user/query/wang
    @GetMapping(value = "/query/{name}/{age}") // PathVariable获取url中多个参数实例，对应访问链接为 localhost:8080/user/query/wang/20
    public User query(@PathVariable String name,
                      @PathVariable int age) {
        return userServer.selectUserByName(name);
    }

    // RequestParam example
    @GetMapping(value = "/query1")
    // required 表示是否必须传入此参数，默认为true;defaultValue表示不传此参数时默认值
    public User query1(@RequestParam(value = "name", required = false, defaultValue = "wang") String name,
                       @RequestParam(value = "age") int age) {
        return userServer.selectUserByName(name);
    }

    // RequestBody example
    // RequestBody 用来接收前端传递给后端的json字符串
    // RequestBody 最多只能有一个，而上面的两个却可以有多个
    @PostMapping(value = "/query2")
    public User query2(@RequestBody User user) {
        return userServer.selectUserByName(user.getName());
    }

    /*
    当通过Postman传递如下json请求体时:
    {
    "name":"wang",
    "grade":4,
    "id":1
    }
    下方返回给postman的只有:
    {
    "id": 1,
    "name": "wang",
    "age": 0,
    "money": 0.0
    }
    重点：（也就是说）
    RequestBody接收前端请求体传递过来的接收对象并映射到User类中，
    如果User类中包含前端请求体中的key，则使用；如果不存在，则忽略；
    同时返回其他属性的默认值，可能是0或null
     */
    @PostMapping(value = "/query3")
    public User query3(@RequestBody User user) {
        return user;
//        return userServer.selectUserByName(user.getName());
    }

    //    @PostMapping(value = "/query3") 方法可以依旧是"query3"(多态的原因)，但是postmapping的参数却不可以重复
    @PostMapping(value = "/query4")
    public String query3(@RequestBody String jsonString) {
        return jsonString;
    }


    // json字符串转json对象
    // 使用JSON.parseObject需要安装fastjson
    // 下述方法都使用了fastjson
    @PostMapping(value = "/query5")
    public String query5(@RequestBody String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        return jsonObject.toJSONString();
    }

    // 直接返回json对象给前端
    @PostMapping(value = "/query6")
    public JSONObject query6(@RequestBody JSONObject jsonObject) {
        return jsonObject;
    }

    // 根据key获取json对象中的具体value
    @PostMapping(value = "/query7")
    public String query7(@RequestBody JSONObject jsonObject) {
        String name = (String) jsonObject.get("name");
        String name1 = jsonObject.get("name").toString();
        String name2 = jsonObject.getString("name").toString();
        return name2;
    }


    @PostMapping(value = "/query8")
    public User query8(@RequestBody JSONObject jsonObject) {
        User name = userServer.selectUserByName(jsonObject.getString("name"));
        return name;
    }

    // map 转成 string
    @PostMapping(value = "/query9")
    public String query9() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "wang");
        map.put("age", 20);
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

    // map 转成 jsonobject
    @PostMapping(value = "/query10")
    public JSONObject query10(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "wang");
        map.put("age", 20);
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
        return jsonObject;
    }

    // jsonobject 转 map
    @PostMapping(value = "/query11")
    public Map query11(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "wang");
        map.put("age", 20);
        map.put("grade", 20);
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));

        Map map1 = JSONObject.toJavaObject(jsonObject, Map.class);
        return map1;
    }

    /*
    json: {"name":"Casey", "age":23, "job":"Teacher"}
    map: {name = Casey, age = 23, job = Teacher}

    JSONArray: [{"name":"Casey", "age":23}, {"name":"Allen", "age":25}]
    ArrayList: [{name = Casey, age = 23}, {name = Allen, age = 25}] ?? 有这种结构

    JSON
    1、JSONObject（一般叫做JSON）指的是JSON对象，表现为键值对（key:value），一个key对应一个value，key不能重复。可以将JSON理解为一种特殊的字符串，只不过是用了特定的符号进行了标注而已。
    2、形式：{“key” : “value”}，key和value都必须用双引号引起来。例如： {“name”: “Michael”}
    3、创建格式：JSONObject json = new JSONObject();

    Map
    1、Map也是键值对，不过表现为{key=value}，并且key和value不需要用双引号引起来。例如：{name=Michael,age=21}。
    2、Map的key可以为对象，JSON的key只能为string。JSON相当于是一种特殊的Map。
    3、创建格式：Map map = new HashMap();

    JSONArray
    1、JSONArray指的是JSON数组，用[]表示，里面可以套上JSON，例如：[ {“name”: “Michael”}, {“name”: “Jake”}]。
    2、创建格式：JSONArray jsonArray = new JSONArray();

    ArrayList
    1、List是一个接口，ArrayList是List接口的一个实现类。例如：[Michael,Jake]。
    2、创建格式：List list = new ArrayList();
     */
}
