package com.clacjz.tank;

public class FourDirectionFireStrategy  implements FireStrategy {
    @Override
    public void fire(Tank t) {
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs){
            new Bullet(t.x, t.y, dir, t.group, t.tf);
        }

    }
}
