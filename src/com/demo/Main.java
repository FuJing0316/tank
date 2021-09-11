package com.demo;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        for (int i = 0; i < 5; i++) {
            tf.enemies.add(new Tank(50 + i * 100, 200, Direction.DOWN, tf));
        }

        //实现自动移动：睡50毫秒，坐标位置改变
        while (true){
            Thread.sleep(50);
            tf.repaint();//会自动调用component.paint(tf.paint)方法重画窗口
        }
    }
}
