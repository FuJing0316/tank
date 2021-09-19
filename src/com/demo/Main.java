package com.demo;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int badTankCount = Integer.parseInt(PropertyMgr.get("badTankCount"));

        //初始化敌方坦克
        for (int i = 0; i < badTankCount; i++) {
            tf.enemies.add(new Tank(50 + i * 100, 200, Direction.DOWN, tf,Group.BAD));
        }

        //实现自动移动：睡50毫秒，坐标位置改变
        while (true){
            Thread.sleep(25);
            tf.repaint();//会自动调用component.paint(tf.paint)方法重画窗口
        }
    }
}
