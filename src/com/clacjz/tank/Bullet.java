package com.clacjz.tank;

import java.awt.*;

public class Bullet {
    private static final int speed = Integer.parseInt((String) PropertyMgr.get("bulletSpeed"));

    private int x, y;
    private Dir dir;
    private TankFrame tankFrame = null;

    private Group group = Group.BAD;
    public static int width = 10;
    public static int height = 10;

    Rectangle rect = new Rectangle();

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

        tankFrame.bullets.add(this);

        rect.x = rect.x;
        rect.y = rect.y;
        rect.width = width;
        rect.height = height;
    }

    public void paint(Graphics g) {

        if (!living) {
            tankFrame.bullets.remove(this);
        }

        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.bulletU, x + Tank.WIDTH / 2 - 6, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x + Tank.WIDTH / 2 - 6, y + Tank.HEIGHT, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y + Tank.HEIGHT / 2 - 6, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x + Tank.WIDTH, y + Tank.HEIGHT / 2 - 4, null);
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

        rect.x = this.x;
        rect.y = this.y;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }


    public void collideWith(Tank tank) {

        if (this.group == tank.group) {
            return;
        }
        if (rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            int ex = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int ey = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            tankFrame.explodes.add(new Explode(ex, ey, tankFrame));
        }
    }

    private void die() {
        this.living = false;
    }
}
