package com.xzy.serializable;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Properties;

/**
 * @author xiazhengyue
 * @since 2020-04-07
 */
@AllArgsConstructor
@ToString
public class User {

    public final String test = "123";

    public String name;
    private int age;
    private Boolean sex;
    private Properties prop;

    public User() {
        System.out.println("User() is called");
    }

    public void setAge(int age) {
        System.out.println("setAge() is called");
        this.age = age;
    }

    public Boolean getSex() {
        System.out.println("getSex() is called");
        return this.sex;
    }

    public Properties getProp() {
        System.out.println("getProp() is called");
        return this.prop;
    }

    public static void main(String[] args) {
        String jsonStr = "{\"name\":\"Tom\", \"age\": 13, \"prop\": {}, \"sex\": 1}";
        Object obj = JSON.parseObject(jsonStr, User.class);
        System.out.println(obj);
    }
}
