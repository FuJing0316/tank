package com.demo;

import java.awt.*;
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
        g.fillRect(x, x, 50, 50);
        x += 10;
        y += 10;
    }

}
