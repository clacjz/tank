package com.clacjz.tank;

import java.awt.*;

public class Tank {
    private int x, y;

    private TankFrame tf = null;
    private Dir dir = Dir.UP;
    private static final int speed = 5;

    private boolean moving = false;


    public Tank(int x, int y, Dir dir, TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
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

    public void fire(){
        tf.bullets.add(new Bullet(this.x, this.y, this.dir, tf));
    }

    public void paint(Graphics g){
        switch (dir){
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        if (!moving){
            return;
        }
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
}
