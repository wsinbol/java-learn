package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private int id;
    private String name;
    private int age;
    private double money;
    @TableField(value = "姓名", typeHandler = com.example.demo.handler.ListToVarcharTypeHandler.class)
    private List<String> tag;

}
