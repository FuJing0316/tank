package com.demo;

import java.awt.*;
import java.util.Random;

/**
 * @Author: fujing
 * @Date: 2021/9/4
 * @Description: 抽象出坦克对象
 * @Version: 1.0
 */
public class Tank {
    //位置坐标
    private int x, y;
    //方向
    Direction dir;
    //速度（每次按键位移）
    private static final int SPEED = 3;
    //静止/移动
    private boolean moving = true;

    //按下control键，坦克要开火，则必须要持有一个TankFrame引用，通过tankframe把坦克射击的子弹传递给窗口，画出来
    private TankFrame tf;

    //是否被消灭
    private boolean living = true;
    //所在队伍：敌我方区分
    private Group group = Group.BAD;

    public static int TANK_WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int TANK_HEIGHT = ResourceMgr.goodTankU.getHeight();

    private Random random = new Random();
    Rectangle tankRect = new Rectangle();


    public Tank(int x, int y, Direction dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        this.tankRect.x = this.x;
        this.tankRect.y = this.y;
        this.tankRect.width = TANK_WIDTH;
        this.tankRect.height = TANK_HEIGHT;
    }

    /**
     * 绘制坦克的方法，在tf.paint中调用
     * 1、如果坦克被子弹击中，则坦克死(死掉的坦克应及时从list移除，否则会导致堆内存泄漏)
     *
     * @param g
     */
    public void paint(Graphics g) {
        if (!living) {
            tf.enemies.remove(this);
        }

        //坦克使用resourceMgr加载的图片
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            default:
                break;
        }

        move();
    }

    private void move() {
        if (!moving) return;

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

        //敌人坦克随机开火
        if (this.group == group.BAD && random.nextInt(100) > 95) fire();
        //敌人坦克移动方向随机
        if (this.group == group.BAD && random.nextInt(100) > 95) randomDir();

        //边界检测，避免坦克开出游戏窗口
        boundarycheck();

        //update tankRect
        tankRect.x = x;
        tankRect.y = y;

    }

    private void boundarycheck() {
        if (this.x < 2) x = 2;
        if (this.y < 30) y = 30;
        if (this.x > TankFrame.FRAME_WIDTH - Tank.TANK_WIDTH - 2) {
            this.x = TankFrame.FRAME_WIDTH - Tank.TANK_WIDTH - 2;
        }
        if (this.y > TankFrame.FRAME_HIGHT - Tank.TANK_HEIGHT - 2) {
            this.y = TankFrame.FRAME_HIGHT - Tank.TANK_HEIGHT - 2;
        }
    }

    private void randomDir() {
        this.dir = Direction.values()[random.nextInt(4)];
    }

    /**
     * 坦克开火，发射子弹
     * 1、确定子弹发射出的位置，子弹方向
     */
    public void fire() {
        int bx = this.x + ResourceMgr.goodTankU.getWidth() / 2;
        int by = this.y + ResourceMgr.goodTankU.getHeight() / 2;
        tf.bulltes.add(new Bullte(bx, by, dir, tf, group));
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
