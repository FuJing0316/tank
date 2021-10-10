package com.demo;

import java.io.Serializable;

/**
 * @Author: fujing
 * @Date: 2021/9/23
 * @Description: 策略模式之-默认开火模式（单方向开火）
 * @Version: 1.0
 */
public class DefaultFireStrategy implements fireStategy, Serializable {
    @Override
    public void fire(Tank tank) {
        int bx = tank.x + ResourceMgr.goodTankU.getWidth() / 2;
        int by = tank.y + ResourceMgr.goodTankU.getHeight() / 2;
        new Bullte(bx, by, tank.dir, tank.tf, tank.group);
    }
}
