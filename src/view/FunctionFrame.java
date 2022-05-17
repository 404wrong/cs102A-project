package view;

import controller.GameController;
import controller.UserController;

import javax.swing.*;
import java.awt.*;

public class FunctionFrame extends JFrame {
    private int WIDTH;
    private int HEIGTH;
    ChessGameFrame mainFrame;


    public FunctionFrame(int width, int height) {
        setTitle("Choose Your Game");
        this.WIDTH = width;
        this.HEIGTH = height;
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        UserController uc = GameController.getUserController();
        addAIButton();
        add1V1Button();

        //加入背景图片
        ImageIcon img = new ImageIcon("backGroundImages/MainFrame2/gameBackground2.jpg");//这是背景图片
        img.setImage(img.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
        JLabel imgLabel = new JLabel(img);//将背景图放在标签里。
        getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
        imgLabel.setBounds(0,0,WIDTH,HEIGTH);//设置背景标签的位置
        Container cp=getContentPane();
        cp.setLayout(new BorderLayout());
        ((JPanel)cp).setOpaque(false);

        GameController.setFunctionFrame(this);
        GameController.setFunctionFrameExist(GameController.getFunctionFrameExist()+1);
    }

    public void addAIButton() {
        JButton jButton = new JButton("AI");
        jButton.setLocation(10, 10);
        jButton.setSize(WIDTH - 20, HEIGTH / 2 - 20);
        jButton.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(jButton);
//todo
        jButton.addActionListener(e -> {
            System.out.println("Click AI");
            mainFrame = new ChessGameFrame(1000, 760);
            this.setVisible(false);
            mainFrame.setVisible(true);
        });
    }

    public void add1V1Button() {
        JButton jButton = new JButton("Two Player Battle");
        jButton.setLocation(10, HEIGTH / 2 + 10);
        jButton.setSize(WIDTH - 20, HEIGTH / 2 - 20);
        jButton.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(jButton);
//todo
        jButton.addActionListener(e -> {
            System.out.println("Click 1V1");
            this.setVisible(false);
            SignInPlayer2Frame signInPlayer2Frame =new SignInPlayer2Frame(300, 200);
            signInPlayer2Frame.setVisible(true);
        });
    }
}