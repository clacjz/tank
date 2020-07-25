package com.clacjz.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author clacjz
 */
public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 200, Dir.UP, Group.GOOD, this);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();
    Explode e = new Explode(100, 100, this);


  //  Bullet b = new Bullet(300, 300, Dir.DOWN, );

    static final int GAME_WIDTH = Integer.parseInt((String) PropertyMgr.get("gameWidth"));
    static final int GAME_HEIGHT = Integer.parseInt((String) PropertyMgr.get("gameHeight"));

    public TankFrame() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
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

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量" + bullets.size(), 10, 60);
        g.drawString("敌方坦克的数量" + tanks.size(), 10, 80);
        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < tanks.size(); i++){
            tanks.get(i).paint(g);
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

//        e.paint(g);
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
                case KeyEvent.VK_A:
                    bL = true;
                    break;
                case KeyEvent.VK_D:
                    bR = true;
                    break;
                case KeyEvent.VK_W:
                    bU = true;
                    break;
                case KeyEvent.VK_S:
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
                case KeyEvent.VK_W:
                    bU = false;
                    break;
                case KeyEvent.VK_S:
                    bD = false;
                    break;
                case KeyEvent.VK_A:
                    bL = false;
                    break;
                case KeyEvent.VK_D:
                    bR = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
    }
}
