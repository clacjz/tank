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
    public TankFrame() {
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
    final int speed;
    Dir dir = null;

    @Override
    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);

        switch (dir){
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            default:
                break;
        }
    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;
        private void setMainTankDir(){
            if (bL){
                dir = Dir.LEFT;
            }
            if (bU){
                dir = Dir.UP;
            }
            if (bR){
                dir = Dir.RIGHT;
            }
            if (bD){
                dir = Dir.DOWN;
            }
        }
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
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
//            x += 30;
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
//            System.out.println("key release");
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
    }
}
