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
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_DEFAULT);
        buffImage = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g = buffImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_DEFAULT);
        g.drawImage(image, 0, 0, width, height, null);
        g.setColor(Color.BLACK);//设定文字颜色
        g.setFont(new Font("微软雅黑", Font.BOLD, size));
        g.drawString(str, width / 4, height / 2 + width*2 / 5 / str.length());
        return buffImage;
    }
}