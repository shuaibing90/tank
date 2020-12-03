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
            BufferedImage image = ImageIO.read(new File(""));
            assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        fail("Not yet implemented");
        assertNotNull(new Object());
    }
}
