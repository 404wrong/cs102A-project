package view;

import controller.*;

import javax.swing.*;
import java.awt.*;


public class SignInPlayer2Frame extends JFrame {
    private int WIDTH;
    private int HEIGTH;
    ChessGameFrame mainFrame;

    public SignInPlayer2Frame(int width, int height) {
        setTitle("SignInUser2");
        this.WIDTH = width;
        this.HEIGTH = height;
        UserController uc = GameController.getUserController();

        JLabel name = new JLabel("Name");
        name.setLocation(WIDTH / 2 - 150, 0);
        name.setSize(50, 20);
        name.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(name);

        JLabel password = new JLabel("Password");
        password.setLocation(WIDTH / 2 - 150, HEIGTH * 2 / 5);
        password.setSize(100, 20);
        password.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(password);

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JTextField Name = new JTextField();
        Name.setLocation(WIDTH / 2 - 75, 0);
        Name.setSize(200, 20);
        Name.setFont(new Font("Name", Font.BOLD, 20));
        add(Name);

        JTextField Password = new JPasswordField();
        Password.setLocation(WIDTH / 2 - 75, HEIGTH * 2 / 5);
        Password.setSize(200, 20);
        Password.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(Password);

        JButton signIn = new JButton("SignIn");
        signIn.setLocation(WIDTH / 2 - 50, HEIGTH * 3 / 5);
        signIn.setSize(100, 20);
        signIn.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(signIn);

        signIn.addActionListener(e -> {
            System.out.println("Click sign in");
            switch (uc.hasThisUser(Name.getText(), String.valueOf(Password.getText().hashCode()))) {
                case 2:
                    JOptionPane.showMessageDialog(null, "Please input your name!","error",JOptionPane.ERROR_MESSAGE);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Please input your password!","error",JOptionPane.ERROR_MESSAGE);
                    break;
                case 1:
                    GameController.setUser2(Name.getText());
                    mainFrame = new ChessGameFrame(1000, 760);
                    this.setVisible(false);
                    mainFrame.setVisible(true);
                    break;
                case -1:
                    JOptionPane.showMessageDialog(null, "The user or the password is wrong!","error",JOptionPane.ERROR_MESSAGE);
                    break;
                case 0:
                    int newUser =JOptionPane.showOptionDialog(null, "Do you want to create a new user?", "create a new user",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,new String[]{"YES","NO"},"YES");
                    if (newUser==0){
                        GameController.setUser2(Name.getText());
                        uc.addThisUser(Name.getText(),String.valueOf(Password.getText().hashCode()),"resource/user.txt");
                        mainFrame = new ChessGameFrame(1000, 760);
                        this.setVisible(false);
                        mainFrame.setVisible(true);
                        break;
                    }break;
            }
        });
    }
}
