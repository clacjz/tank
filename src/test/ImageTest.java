package test;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static  org.junit.jupiter.api.Assertions.*;

public class ImageTest {
    @Test
    public void Test(){
        try {
            BufferedImage image = ImageIO.read(new File("C:/Users/clacjz/IdeaProjects/tank/src/image"));
            assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //fail("Not yet implemented");

    }
}
