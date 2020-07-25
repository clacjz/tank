package com.clacjz.tank;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
//        int bx = t.x + Tank.WIDTH / 2 - Bullet.width / 2;
//        int by = t.y + Tank.HEIGHT / 2 - Bullet.height / 2;

        new Bullet(t.x, t.y, t.dir, t.group, t.tf);

    }
}
