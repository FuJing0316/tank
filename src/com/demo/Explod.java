package com.demo;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author: fujing
 * @Date: 2021/9/11
 * @Description: 爆炸效果对象
 * @Version: 1.0
 */
public class Explod {
    private static final int EXPLOD_WIDTH = ResourceMgr.bufferedImages[0].getWidth();
    private static final int EXPLOD_HEIGHT = ResourceMgr.bufferedImages[0].getHeight();

    private int x, y;
    private TankFrame tf;
    private int step = 0;

    public Explod(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if (step >= ResourceMgr.bufferedImages.length) return;
        g.drawImage(ResourceMgr.bufferedImages[step++], x, y, null);
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

}
