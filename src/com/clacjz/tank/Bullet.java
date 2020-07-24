package com.clacjz.tank;

import java.awt.*;

public class Bullet {
    private static final int speed = 10;

    private int x, y;
    private Dir dir;
    private TankFrame tankFrame = null;

    private Group group = Group.BAD;
    private static int width = 10;
    private static int height = 10;

    private boolean living = true;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    public void paint(Graphics g) {

        if (!living) {
            tankFrame.bullets.remove(this);
        }

        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.bulletU, x + 20, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x + 18, y + 40, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y + 22, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x + 40, y + 23, null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {

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

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }


    public void collideWith(Tank tank) {

        if (this.group == tank.group){
            return;
        }
        Rectangle rect1 = new Rectangle(this.x + 20, this.y, width, height);
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight());
        if (rect1.intersects(rect2)){
            tank.die();
            this.die();
            tankFrame.explodes.add(new Explode(x, y, tankFrame));
        }
    }

    private void die() {
        this.living = false;
    }
}
