package view;

import controller.*;
import addModel.TransparentButton;

import javax.swing.*;
import java.awt.*;


public class SignInPlayer1Frame extends JFrame {
    private int WIDTH;
    private int HEIGTH;
    FunctionFrame functionFrame;

    public SignInPlayer1Frame(int width, int height) {
        setTitle("SignIn");
        this.WIDTH = width;
        this.HEIGTH = height;
        UserController uc = new UserController("resource/user.txt");
        GameController.setUserController(uc);

        //加入背景图片
        ImageIcon img = new ImageIcon("backGroundImages/MainFrame2/gameBackground2.jpg");//这是背景图片
        img.setImage(img.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
        JLabel imgLabel = new JLabel(img);//将背景图放在标签里。
        getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
        imgLabel.setBounds(0,0,WIDTH,HEIGTH);//设置背景标签的位置
        Container cp=getContentPane();
        cp.setLayout(new BorderLayout());
        ((JPanel)cp).setOpaque(false);

        JLabel name = new JLabel("Name");
        name.setForeground(new Color(255,255,255));
        name.setLocation(WIDTH / 2 - 150, 0);
        name.setSize(50, 20);
        name.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(name);

        JLabel password = new JLabel("Password");
        password.setForeground(new Color(255,255,255));
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

        JButton signIn =new TransparentButton(1,3,4,3,"SignIn",100,20);
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
                    GameController.setUser1(Name.getText());
                    functionFrame = new FunctionFrame(200, 300);
                    this.setVisible(false);
                    functionFrame.setVisible(true);
                    break;
                case -1:
                    JOptionPane.showMessageDialog(null, "The user or the password is wrong!","error",JOptionPane.ERROR_MESSAGE);
                    break;
                case 0:
                    int newUser =JOptionPane.showOptionDialog(null, "Do you want to create a new user?", "create a new user",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,new String[]{"YES","NO"},"YES");
                    if (newUser==0){
                        GameController.setUser1(Name.getText());
                        uc.addThisUser(Name.getText(),String.valueOf(Password.getText().hashCode()),"resource/user.txt");
                        functionFrame = new FunctionFrame(200, 300);
                        this.setVisible(false);
                        functionFrame.setVisible(true);
                        break;
                    }break;
            }
        });
    }
}
