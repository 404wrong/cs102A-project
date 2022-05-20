package addModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.LinkedTransferQueue;

public class TransparentButton extends JButton{
    private static final ImageIcon[] persent={
            new ImageIcon("purewhite/0.png"),
            new ImageIcon("purewhite/25.png"),
            new ImageIcon("purewhite/50.png"),
            new ImageIcon("purewhite/75.png"),
            new ImageIcon("purewhite/100.png")
    };
    public TransparentButton(int d, int r,int p,int s){
        super();
        setEnabled(true);
        setIcon(persent[d]);
        setPressedIcon(persent[p]);
        setRolloverIcon(persent[r]);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setMargin(new Insets(0,0,0,0));
        setRolloverSelectedIcon(persent[r]);
        setSelectedIcon(persent[s]);
    }

    public TransparentButton(int d, int r,int p,int s,String str,int width,int height,int size){
        super();
        setEnabled(true);
        try {
            setIcon(new ImageIcon(ImageAddWord.drawWord(width,height,str,persent[d].getImage(),size)));
            setPressedIcon(new ImageIcon(ImageAddWord.drawWord(width,height,str,persent[p].getImage(),size)));
            setRolloverIcon(new ImageIcon(ImageAddWord.drawWord(width,height,str,persent[r].getImage(),size)));
            setRolloverSelectedIcon(new ImageIcon(ImageAddWord.drawWord(width,height,str,persent[r].getImage(),size)));
            setSelectedIcon(new ImageIcon(ImageAddWord.drawWord(width,height,str,persent[s].getImage(),size)));
        }catch (IOException e){}
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setMargin(new Insets(0,0,0,0));
    }
}
