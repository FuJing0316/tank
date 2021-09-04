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
    private static final int SPEED = 1;
    //是否移动属性:初始状态是静止的
    private boolean isMoving = false;

    //按下control键，坦克要开火，则必须要持有一个TankFrame引用，通过tankframe把坦克射击的子弹传递给窗口，画出来
    private TankFrame tf;

    public Tank(int x, int y, Direction dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    //定义画坦克的方法，在tankframe.paint方法中被调用
    public void paint(Graphics g) {
/*        g.setColor(Color.YELLOW);
        //在x,y坐标位置画一个黑色小块（坦克），宽50高50
        g.fillRect(x, y, 50, 50);*/

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

    //当前坦克要开火，发出一个子弹，则子弹的坐标位置应和坦克的位置相同
    public void fire() {
        tf.bulltes.add(new Bullte(this.x, this.y, this.dir, tf));
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
