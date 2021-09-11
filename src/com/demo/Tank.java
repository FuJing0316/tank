package com.demo;

import java.awt.*;

/**
 * @Author: fujing
 * @Date: 2021/9/4
 * @Description: 抽象出坦克对象
 * @Version: 1.0
 */
public class Tank {
    //增加一行
    //坦克位置坐标
    private int x,y;
    //移动方向
    Direction dir;
    //坦克移动速度（每次按键位移）
    private static final int SPEED = 2;
    //是否移动属性:初始状态是静止的
    private boolean isMoving = false;

    //按下control键，坦克要开火，则必须要持有一个TankFrame引用，通过tankframe把坦克射击的子弹传递给窗口，画出来
    private TankFrame tf;

    //是否被消灭
    private boolean living = true;

    public static int TANK_WIDTH = ResourceMgr.tankD.getWidth();
    public static int TANK_HEIGHT = ResourceMgr.tankD.getHeight();

    public Tank(int x, int y, Direction dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    /**
     * 绘制坦克的方法，在tf.paint中调用
     * 1、如果坦克被子弹击中，则坦克死(死掉的坦克应及时从list移除，否则会导致堆内存泄漏)
     * @param g
     */
    public void paint(Graphics g) {
        if (!living){
            tf.enemies.remove(this);
        }

        //坦克使用resourceMgr加载的图片
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            default:
                break;
        }

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

    /**
     * 坦克开火，发射子弹
     * 1、确定子弹发射出的位置，子弹方向
     */
    public void fire() {
        int bx = this.x + 2 * ResourceMgr.bulletD.getWidth() - ResourceMgr.bulletD.getWidth() / 2;
        int by = this.y + 2 * ResourceMgr.bulletD.getHeight() - ResourceMgr.bulletD.getHeight() / 2;
        tf.bulltes.add(new Bullte(bx, by, dir, tf));
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


    public void die() {
        living = false;
    }
}
