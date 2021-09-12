package com.demo;

import java.awt.*;

/**
 * @Author: fujing
 * @Date: 2021/9/4
 * @Description: 子弹对象
 * @Version: 1.0
 */
public class Bullte {
    private int x, y;
    Direction dir;
    //位移大小（也就是移动速度）
    private static final int SPEED = 6;

    private TankFrame tf;
    private boolean living = true;

    private static final int BULLET_WIDTH = ResourceMgr.bulletD.getWidth();
    private static final int BULLET_HEIGHT = ResourceMgr.bulletD.getHeight();

    private Group group = Group.BAD;

    public Bullte(int x, int y, Direction dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    /**
     * 定义子弹画法，在tankframe.paint方法中被调用
     * 1、判定子弹是否存活，未存活的子弹，从子弹列表移除
     * 2、子弹由磁盘图片加载
     * 3、设置子弹移动速度、是否存活属性
     *
     * @param g
     */
    public void paint(Graphics g) {
        if (!living) {
            tf.bulltes.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
                break;
        }

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
        if (x < 0 || y < 0 || x > TankFrame.FRAME_WIDTH || y > TankFrame.FRAME_HIGHT) {
            living = false;
        }
    }

    /**
     * 检测子弹与坦克之间的碰撞(子弹是否打中坦克)
     * 有碰撞则：子弹死坦克死
     */
    public void collectDieWith(Tank tank) {
        if (this.group == tank.getGroup()) return;
        Rectangle rectangle1 = new Rectangle(this.x, this.y, BULLET_WIDTH, BULLET_HEIGHT);
        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), Tank.TANK_WIDTH, Tank.TANK_HEIGHT);
        //检测子弹和坦克之间的碰撞(交集)
        if (rectangle1.intersects(rectangle2)) {
            tank.die();
            this.die();

            int ex = tank.getX() + Tank.TANK_WIDTH / 2 - Explod.EXPLOD_WIDTH / 2;
            int ey = tank.getX() + Tank.TANK_HEIGHT / 2 - Explod.EXPLOD_HEIGHT / 2;
            tf.explods.add(new Explod(ex, ey, tf));
        }
    }

    private void die() {
        living = false;
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
