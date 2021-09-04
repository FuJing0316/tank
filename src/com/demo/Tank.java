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

    public Tank(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    //画出一个坦克的方法
    public void paint(Graphics g) {
        System.out.println("paint");
        //在x,y坐标位置画一个黑色小块（坦克），宽50高50
        g.fillRect(x, y, 50, 50);

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
