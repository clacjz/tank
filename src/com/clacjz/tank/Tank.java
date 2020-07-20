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
        Color c = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 50, 50);
        g.setColor(c);
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
