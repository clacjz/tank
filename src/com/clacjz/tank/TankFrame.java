package com.clacjz.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author clacjz
 */
public class TankFrame extends Frame {
    public TankFrame(){
        this.setTitle("Tank War");
        this.setSize(800, 600);
        this.setVisible(true);
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.addKeyListener(new MyKeyListener());
    }

    int x = 200, y = 200;

    @Override
    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);
        x += 10;
//        y += 10;
    }

    class MyKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
//            x += 30;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("key release");
        }
    }


}
