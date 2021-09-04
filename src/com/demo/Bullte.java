package com.demo;

import java.awt.*;

/**
 * @Author: fujing
 * @Date: 2021/9/4
 * @Description: 子弹对象
 * @Version: 1.0
 */
public class Bullte {
    private int x;
    private int y;
    Direction dir;
    //位移大小（也就是移动速度）
    private static final int SPEED = 10;



    public Bullte(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    //定义画子弹的方法，在tankframe.paint方法中被调用
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 20, 20);

        move();
    }

    private void move() {
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
