package com.clacjz.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage badTankL, badTankR, badTankU, badTankD;
    public static BufferedImage goodTankL, goodTankR, goodTankU, goodTankD;
    public static BufferedImage bulletL, bulletR, bulletU, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/badTank1.png"));
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);

            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/goodTank1.png"));
            goodTankR = ImageUtil.rotateImage(goodTankU, 90 );
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bulletU.gif"));
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        for (int i = 0; i < 16; i++) {
            try {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/e" + (i + 1) + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
