package com.demo;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        //实现自动移动：睡50毫秒，坐标位置改变
        while (true){
            Thread.sleep(100);
            tf.repaint();//会自动调用paint方法重画窗口
        }
    }
}
