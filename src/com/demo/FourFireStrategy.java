package com.demo;

import java.io.Serializable;

/**
 * @Author: fujing
 * @Date: 2021/9/23
 * @Description: 策略模式之-四个方向开火策略
 * @Version: 1.0
 */
public class FourFireStrategy implements fireStategy, Serializable {
    @Override
    public void fire(Tank tank) {
        int bx = tank.x + ResourceMgr.goodTankU.getWidth() / 2;
        int by = tank.y + ResourceMgr.goodTankU.getHeight() / 2;

        Direction[] dir = Direction.values();
        for (Direction direction : dir) {
            new Bullte(bx, by, direction, tank.tf, tank.group);
        }
    }
}
