package com.demo;

import java.awt.*;

/**
 * @Author: fujing
 * @Date: 2021/9/4
 * @Description: 抽象出坦克对象
 * @Version: 1.0
 */
public class Tank {
    //坦克位置坐标x
    private int x;
    //坦克位置坐标y
    private int y;
    //移动方向
    Direction dir;
    //每一次按键的位移大小（也就是速度）
    private static final int SPEED = 10;
    //是否移动属性:初始状态是静止的
    private boolean isMoving = false;

    //按下control键，坦克要开火，则必须要持有一个frame
    private TankFrame tf;

    public Tank(int x, int y, Direction dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    //定义画坦克的方法，在tankframe.paint方法中被调用
    public void paint(Graphics g) {
        g.setColor(Color.YELLOW);
        //在x,y坐标位置画一个黑色小块（坦克），宽50高50
        g.fillRect(x, y, 50, 50);

        move();
    }

    private void move() {
        if (!isMoving) return;

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }

    //当前坦克要开火，发出一个子弹，则子弹的坐标位置应和坦克的位置相同
    public void fire() {
        tf.bullte = new Bullte(this.x,this.y,this.dir);
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }


}
