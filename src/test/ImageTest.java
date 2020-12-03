package test;


import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description TODO
 * @Author Fedeline
 * @Date 2020/12/3 下午1:27
 */
public class ImageTest {
    @Test
    void test(){
        try {

            BufferedImage image = ImageIO.read(new File("/home/xysycx/IdeaProjects/tank/src/images/bulletD.gif"));
            assertNotNull(image);
            BufferedImage image2 = ImageIO.read(new File("src/images/bulletD.gif"));
            assertNotNull(image2);
            BufferedImage image3 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            assertNotNull(image3);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
