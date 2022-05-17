package view;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.LinkedTransferQueue;

public class TransparentButton extends JButton{
    private static final Icon[] persent={
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

    public TransparentButton(int d, int r,int p,String str){
        super();
        setText(str);
        setEnabled(true);
        setIcon(persent[d]);
        setPressedIcon(persent[p]);
        setRolloverIcon(persent[r]);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setMargin(new Insets(0,0,0,0));
        setRolloverSelectedIcon(persent[p]);
        setSelectedIcon(persent[d]);
    }
    public static JButton addWhiteButton(int d, int r,int p){
        JButton a=new JButton();
        a.setEnabled(true);
        a.setIcon(persent[d]);
        a.setPressedIcon(persent[p]);
        a.setRolloverIcon(persent[r]);
        a.setBorderPainted(false);
        a.setContentAreaFilled(false);
        a.setFocusPainted(false);
        a.setMargin(new Insets(0,0,0,0));
        a.setRolloverSelectedIcon(persent[p]);
        a.setSelectedIcon(persent[d]);
        return a;
    }

    public static JButton addWhiteButton(int d, int r,int p,String str){
        JButton a=new JButton(str);
        a.setEnabled(true);
        a.setIcon(persent[d]);
        a.setPressedIcon(persent[p]);
        a.setRolloverIcon(persent[r]);
        a.setBorderPainted(false);
        a.setContentAreaFilled(false);
        a.setFocusPainted(false);
        a.setMargin(new Insets(0,0,0,0));
        a.setRolloverSelectedIcon(persent[p]);
        a.setSelectedIcon(persent[d]);
        return a;
    }

}
