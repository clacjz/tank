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

    Tank myTank = new Tank(200, 200, Dir.UP);
    Bullet b = new Bullet(300, 300, Dir.DOWN);

    static final int GAME_WIDTH = 800;
    static final int GAME_HEIGHT = 600;

    public TankFrame() {
        this.setTitle("Tank War");
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
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

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        b.paint(g);
    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        public void setMainTankDir(){
            if (!bU && !bL && !bR && !bD){
                myTank.setMoving(false);
            }else {
                myTank.setMoving(true);

                if (bL){
                    myTank.setDir(Dir.LEFT);
                }
                if (bU){
                    myTank.setDir(Dir.UP);
                }
                if (bR){
                    myTank.setDir(Dir.RIGHT);
                }
                if (bD){
                    myTank.setDir(Dir.DOWN);
                }
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
