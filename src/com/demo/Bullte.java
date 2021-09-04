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

    private TankFrame tf;
    private  static boolean isLive = true;


    public Bullte(int x, int y, Direction dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    /**
     * 定义子弹画法，在tankframe.paint方法中被调用
     * @param g
     */
    public void paint(Graphics g) {
        if (!isLive){
            tf.bulltes.remove(this);
        }
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

        //判断子弹是否存活，已经死掉的子弹，要及时清理调，否则会内存溢出
        if (x < 0 || y < 0 || x > tf.FRAME_WIDTH || y > tf.FRAME_HIGHT) {
            isLive = false;
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
