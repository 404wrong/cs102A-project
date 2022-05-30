package view;

import addModel.TransparentButton;
import controller.AIController;
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
//        addAIButton();
        add1V1Button();
        addRankingButton();
        addAIButton();

        //加入背景图片
        ImageIcon img = new ImageIcon("backGroundImages/MainFrame2/gameBackground2.jpg");//这是背景图片
        img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        JLabel imgLabel = new JLabel(img);//将背景图放在标签里。
        getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
        imgLabel.setBounds(0, 0, WIDTH, HEIGTH);//设置背景标签的位置
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        ((JPanel) cp).setOpaque(false);

        GameController.setFunctionFrame(this);
        GameController.setFunctionFrameExist(GameController.getFunctionFrameExist() + 1);
    }

    public void addAIButton() {
        JButton jButton = new TransparentButton(2, 3, 4, 3, "AIController", 200, 60, 15);
        jButton.setLocation(130, 175);
        jButton.setSize(200, 60);
        jButton.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(jButton);
        AIController aiController1 = new AIController(0);
//todo
        jButton.addActionListener(e -> {
//            System.out.println("Click AIController");
            int difficulites = JOptionPane.showOptionDialog(null, "Select the difficulty", "Select the difficulty", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Simple", "easy", "Hard"}, "Simple");
            AIController aiController = new AIController(difficulites);
            mainFrame = new ChessGameFrame(1000, 760);
            this.setVisible(false);
            mainFrame.setVisible(true);
            GameController.setUser2("AIController");
        });
    }

    public void add1V1Button() {
        JButton jButton = new TransparentButton(2, 3, 4, 3, "Two Player Battle", 200, 60, 15);
        jButton.setLocation(130, 75);
        jButton.setSize(200, 60);
        jButton.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(jButton);
//todo
        jButton.addActionListener(e -> {
//            System.out.println("Click 1V1");
            this.setVisible(false);
            SignInPlayer2Frame signInPlayer2Frame = new SignInPlayer2Frame(300, 200);
            signInPlayer2Frame.setVisible(true);
        });
    }

    private void addRankingButton() {
        JButton button = new TransparentButton(2, 3, 4, 3, "Ranking List", 200, 60, 15);
        button.setLocation(130, 250);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
//            System.out.println("Click ranking list");

            JFrame frame = new JFrame("Ranking List"); //这里要用到import javax.swing.JFrame;
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            final ImageIcon imageIcon = new ImageIcon("backGroundImages/MainFrame2/gameBackground2_50.png");//这里要用到import javax.swing.ImageIcon;
            JTextArea textArea = new JTextArea() { //这里要用到import javax.swing.JTextArea;
                Image image = imageIcon.getImage();

                {
                    setOpaque(false);
                } // instance initializer

                public void paint(Graphics g) {
                    g.drawImage(image, 0, 0, 400, 300, null);
                    super.paint(g);
                }
            };
            JScrollPane scrollPane = new JScrollPane(textArea);//这里要用到import javax.swing.JScrollPane;
            Container content = frame.getContentPane();
            content.add(scrollPane, BorderLayout.CENTER);
            textArea.append(GameController.getUserController().toString());
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}