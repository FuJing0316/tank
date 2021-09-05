package com.demo;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: fujing
 * @Date: 2021/8/22
 * @Description:  游戏主界面
 * @Version: 1.0
 */
public class TankFrame extends Frame {
    public final int FRAME_WIDTH = 800;
    public final int FRAME_HIGHT = 600;

    Tank mytank = new Tank(300, 200, Direction.DOWN, this);
    public List<Bullte> bulltes = new ArrayList<>();//子弹列表
    public List<Tank> tanks = new ArrayList<>();//坦克列表

    public TankFrame() {
        setSize(FRAME_WIDTH, FRAME_HIGHT);
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

    /**
     * 处理缓冲，解决闪烁问题
     */
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FRAME_WIDTH, FRAME_HIGHT);
        }

        Graphics gOffscreen = offScreenImage.getGraphics();
        Color color = gOffscreen.getColor();
        gOffscreen.setColor(Color.BLACK);
        gOffscreen.fillRect(0, 0, FRAME_WIDTH, FRAME_HIGHT);
        gOffscreen.setColor(color);

        paint(gOffscreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    //window会自动调用的方法
    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bulltes.size(), 60, 60);
        g.drawString("敌人的数量：" + tanks.size(), 60, 80);
        g.setColor(color);

        mytank.paint(g);//主坦克

        //画出子弹：此处注意内存泄漏
        for (int i = 0; i < bulltes.size(); i++) {
            bulltes.get(i).paint(g);
        }
        //敌人坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        //检测子弹是否击中坦克:每颗子弹每一辆坦克都检查
        for (int i = 0; i < bulltes.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bulltes.get(i).collectDieWith(tanks.get(j));
            }
        }
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
                case KeyEvent.VK_CONTROL:
                    mytank.fire();
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

        /**
         * 根据按键方向，设置坦克位移方向
         */
        private void setMainTankDir() {
            //按下的不是上下左右键，坦克静止
            if (!bL && !bU && !bR && !bD) {
                mytank.setMoving(false);
            } else {
                mytank.setMoving(true);
            }

            if (bL) mytank.setDir(Direction.LEFT);
            if (bU) mytank.setDir(Direction.UP);
            if (bR) mytank.setDir(Direction.RIGHT);
            if (bD) mytank.setDir(Direction.DOWN);
        }

    }
}
