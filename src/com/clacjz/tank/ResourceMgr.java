package com.clacjz.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage tankL, tankR, tankU, tankD;
    public static BufferedImage bulletL, bulletR, bulletU, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/badTank1"));
            tankR = ImageUtil.rotateImage(tankU, 90);
            tankL = ImageUtil.rotateImage(tankU, -90);
            tankD = ImageUtil.rotateImage(tankU, 180);
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
