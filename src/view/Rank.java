package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.security.PublicKey;

public class Rank extends JFrame {
    public Rank(int WIDTH,int HEIGTH) {
        setTitle("Ranking List");

        final ImageIcon imageIcon = new ImageIcon("backGroundImages/MainFrame2/gameBackground2.jpg");//这里要用到import javax.swing.ImageIcon;
        JTextArea tf = new JTextArea() { //这里要用到import javax.swing.JTextArea;
            Image image = imageIcon.getImage();

            Image grayImage = GrayFilter.createDisabledImage(image);//这里要用到import javax.swing.GrayFilter;
            {
                setOpaque(false);
            } // instance initializer

            public void paint(Graphics g) {
                g.drawImage(grayImage, 0, 0, this);
                super.paint(g);
            }
        };
        JScrollPane scrollPane = new JScrollPane(tf);//这里要用到import javax.swing.JScrollPane;
        Container content = this.getContentPane();
        content.add(scrollPane, BorderLayout.CENTER);
        tf.append(GameController.getUserController().toString());
        add(tf,BorderLayout.CENTER);
        setSize(WIDTH,HEIGTH);
    }
}
