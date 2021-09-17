package com.demo;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: fujing
 * @Date: 2021/9/17
 * @Description: 读取配置文件的配置数据
 * @Version: 1.0
 */
public class PropertyMgr {

    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        if (properties == null) {
            return null;
        }
        return properties.get(key);
    }

    public static void main(String[] args) {
        System.out.println("badTankCount:" + get("badTankCount"));
    }
}
