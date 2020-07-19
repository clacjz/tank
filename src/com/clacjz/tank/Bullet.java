package com.clacjz.tank;

import java.awt.*;

public class Bullet {
    private static final int speed = 10;

    private int x, y;
    private Dir dir;

    private static int width = 10;
    private static int height = 10;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
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
    }
}
