package com.demo;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author: fujing
 * @Date: 2021/8/22
 * @Description:
 * @Version: 1.0
 */
public class TankFrame extends Frame {

    Tank tank = new Tank(200, 200, Direction.DOWN);

    public TankFrame() {
        setSize(800, 600);
        setResizable(false);//设置是否可改变窗口大小
        setTitle("Tank War");
        setVisible(true);

        //添加键盘监听事件
        addKeyListener(new MyKeyAdapter());

        //设置window监听事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //window会自动调用的方法
    @Override
    public void paint(Graphics g) {
        tank.paint(g);
    }

    class MyKeyAdapter extends KeyAdapter {
        //定义按键方向
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("key pressed");
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("key released");
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        //根据按键方向，设置坦克位移方向
        private void setMainTankDir() {
            //按下的不是上下左右键，坦克静止
            if (!bL && !bU && !bR && !bD){
                tank.setMoving(false);
            }else {
                tank.setMoving(true);
            }

            if (bL) tank.setDir(Direction.LEFT);
            if (bU) tank.setDir(Direction.UP);
            if (bR) tank.setDir(Direction.RIGHT);
            if (bD) tank.setDir(Direction.DOWN);
        }

    }
}
