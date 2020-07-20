package com.clacjz.tank;

import java.awt.*;

public class Bullet {
    private static final int speed = 10;

    private int x, y;
    private Dir dir;
    private TankFrame tankFrame = null;

    private static int width = 10;
    private static int height = 10;

    private boolean live = true;

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {

        if (!live){
            tankFrame.bullets.remove(this);
        }
        Color color = g.getColor();
        g.setColor(Color.RED);

        g.fillOval(x, y, width, height);
        g.setColor(color);
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
            live = false;
        }
    }


}
