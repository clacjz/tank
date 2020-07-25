package com.clacjz.tank;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Tank {
    int x, y;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    public Group group = Group.BAD;

    TankFrame tf = null;
    Dir dir = Dir.UP;
    private static final int speed = Integer.parseInt((String) PropertyMgr.get("tankSpeed"));

    private boolean moving = true;

    private boolean living = true;

    private Random random = new Random();

    FireStrategy fS = null;

    Rectangle rect = new Rectangle();


    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }

    public static int getSpeed() {
        return speed;
    }


    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.height = HEIGHT;
        rect.width = WIDTH;
        if (group == Group.BAD) {
            String bad = (String) PropertyMgr.get("badTankStrategy");
            try {
                fS = (FireStrategy) Class.forName(bad).getDeclaredConstructor().newInstance();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
            else {
            String good = (String) PropertyMgr.get("goodTankStrategy");
            try {
                fS = (FireStrategy) Class.forName(good).getDeclaredConstructor().newInstance();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        fS.fire(this);
//        int bx = this.x + Tank.WIDTH / 2 - Bullet.width / 2;
//        int by = this.y + Tank.HEIGHT / 2 - Bullet.height / 2;
//
//        tf.bullets.add(new Bullet(this.x, this.y, this.dir, this.group, this.tf));
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.tanks.remove(this);
        }
        switch (dir) {
            case RIGHT:
                if (this.group == Group.GOOD) {
                    g.drawImage(ResourceMgr.goodTankR, x, y, null);
                } else {
                    g.drawImage(ResourceMgr.badTankR, x, y, null);
                }
                break;
            case LEFT:
                if (this.group == Group.GOOD) {
                    g.drawImage(ResourceMgr.goodTankL, x, y, null);
                } else {
                    g.drawImage(ResourceMgr.badTankL, x, y, null);
                }
                break;
            case DOWN:
                if (this.group == Group.GOOD) {
                    g.drawImage(ResourceMgr.goodTankD, x, y, null);
                } else {
                    g.drawImage(ResourceMgr.badTankD, x, y, null);
                }
                break;
            case UP:
                if (this.group == Group.GOOD) {
                    g.drawImage(ResourceMgr.goodTankU, x, y, null);
                } else {
                    g.drawImage(ResourceMgr.badTankU, x, y, null);
                }
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        if (!moving) {
            return;
        }
        switch (dir) {
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

        if (group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }

        if (group == Group.BAD && random.nextInt(10) < 3) {
            randomDir();
        }

        boundsCheck();
        // update rect
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 0) {
            x = 0;
        }
        if (this.y < 30) {
            y = 30;
        }
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) {
            x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        }
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) {
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void die() {
        this.living = false;
    }
}
