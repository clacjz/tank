package com.clacjz.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws Exception {
        TankFrame tankFrame = new TankFrame();

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount")) ;
        for (int i=0; i < initTankCount; i++){
            tankFrame.tanks.add(new Tank(50 + i * 80, 400, Dir.UP, Group.BAD, tankFrame));
        }

        System.out.println();
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
