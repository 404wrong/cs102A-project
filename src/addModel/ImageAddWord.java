package addModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageAddWord {
    public static Image drawWord(int width, int height, String str, Image image,int size) throws IOException {
        BufferedImage buffImage;
        Graphics2D g;
        buffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g = buffImage.createGraphics();
        buffImage = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g = buffImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.setColor(Color.BLACK);//设定文字颜色
        g.setFont(new Font("Microsoft JhengHei", Font.BOLD, size));
        g.drawString(str, width / 4, height / 2 + width*2 / 5 / str.length());
        return buffImage;
    }
}