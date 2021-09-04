package test;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: fujing
 * @Date: 2021/9/4
 * @Description:
 * @Version: 1.0
 */
class ImageTest {

    @Test
    void test() {
        try {
            BufferedImage image = ImageIO.read(new File("C:\\D\\my_codespace\\tank\\src\\images\\bulletD.gif"));
            assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}