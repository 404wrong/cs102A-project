package view;

import controller.GameController;
import controller.UserController;

import javax.swing.*;
import java.awt.*;

public class FunctionFrame extends Frame {
    private int WIDTH;
    private int HEIGTH;
    ChessGameFrame mainFrame;


    public FunctionFrame(int width, int height) {
        setTitle("Choose Your Game");
        this.WIDTH = width;
        this.HEIGTH = height;
        UserController uc= GameController.getUserController();

    }
    public void addNewGameButton(){
        JButton jButton = new JButton("SignIn");
        jButton.setLocation(10, 10);
        jButton.setSize(WIDTH-20, HEIGTH/2-20);
        jButton.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(jButton);
//todo
        jButton.addActionListener(e -> {
            System.out.println("Click sign in");
                    mainFrame = new ChessGameFrame(1000, 760);
                    this.setVisible(false);
                    mainFrame.setVisible(true);
        });
    }
}
