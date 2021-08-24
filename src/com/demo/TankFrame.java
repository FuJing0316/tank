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
    int x = 200;
    int y = 200;

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

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
        //在x=200,y=200的位置画一个黑色小块宽50高50
        g.fillRect(x, y, 50, 50);
        x+=10;//小黑块（坦克）位置移动
    }

    class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("key pressed");
//            x+=200;
//            repaint();//窗口重画，配合键盘按下，坐标的变化，可以实现坦克移动效果
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("key released");
        }

    }
}
