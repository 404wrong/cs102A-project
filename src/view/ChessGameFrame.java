package view;

//import controller.GameController;

import addModel.TransparentButton;
import controller.GameController;
import model.ChessColor;
import model.ChessComponent;
import save_write.Save_Write;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static jdk.jfr.internal.consumer.EventLog.stop;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 * 调用了chessboard。java文件
 * 调用方法 new，setlocation
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    public JLabel gamer;
    int q=0;
    int p=0;
    public void qAdd(){q++;}
//    private GameController gameController;

    public ChessGameFrame(int width, int height) {
        GameController.setChessGameFrame(this);
        setTitle("2022 CS102A Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH ;

        setSize(WIDTH, HEIGTH+30);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addGamer();
        addChessboard();
        addLabel();
        addRankingButton();
        addLoadButton();
        addSaveButton();
        addResetButton();
        addRepentButton();
        addPlaybackButton();

        //加入背景图片
        ImageIcon img = new ImageIcon("backGroundImages/MainFrame2/gameBackground2.jpg");//这是背景图片
        img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        JLabel imgLabel = new JLabel(img);//将背景图放在标签里。
        getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
        imgLabel.setBounds(0, 0, WIDTH, HEIGTH);//设置背景标签的位置
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        ((JPanel) cp).setOpaque(false);

        setVisible(true);
    }

    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE, "resource/save1.txt");
        GameController.setChessboard(chessboard);
        chessboard.setLocation(0,0);
        add(chessboard);
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addLabel() {
        JLabel gamer1 = new JLabel("WHITE  " + GameController.getUser1());
        gamer1.setLocation(HEIGTH, HEIGTH / 10 + 40);
        gamer1.setSize(200, 60);
        gamer1.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(gamer1);
        JLabel gamer2 = new JLabel("BLACK  " + GameController.getUser2());
        gamer2.setLocation(HEIGTH, HEIGTH / 10 + 80);
        gamer2.setSize(200, 60);
        gamer2.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(gamer2);
    }

    public void addGamer() {
        gamer = new JLabel();
        gamer.setLocation(HEIGTH, HEIGTH / 10);
        gamer.setSize(200, 60);
        gamer.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(gamer);
        gamer.repaint();
    }

    private void addPlaybackButton() {
        JButton button = new JButton("Playback");
        button.setLocation(HEIGTH, HEIGTH / 10 + 250);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        GameController.playback=1;

        button.addActionListener(e -> {
            System.out.println("Click Playback");
            GameController.setError(100);
            JFileChooser fileChooser = new JFileChooser(); // 创建一个文件选择器
            fileChooser.setDialogTitle("选择要打开的文件"); // 给它一个标题
            File currentDirectory = new File("resource"); // 默认目录。TODO：改成你电脑上的目录
            fileChooser.setCurrentDirectory(currentDirectory); // 设置文件选择器起始目录为默认目录
            int result = fileChooser.showOpenDialog(button); // 打开文件选择器
            File chosenFile = null;
            if (result == JFileChooser.APPROVE_OPTION) { // 如果最终确认选择而不是推出
                chosenFile = fileChooser.getSelectedFile(); // 获取选中的文件
            }else {
                chosenFile=new File("save1.txt");
                GameController.setError(104);
            }
            try {
                String path = chosenFile.getAbsolutePath();

                Save_Write a = new Save_Write();
                if (!a.convertToChessboard(a.readFileByFileReader(path))) {
                    if (!a.convertToChessboard(a.readFileByFileReader("resource/save1.txt"))) {
                        a.writeFileByFileWriter("resource/save1.txt", new ArrayList<String>() {{add("RNBQKBNRPPPPPPPP________________________________pppppppprnbqkbnrw");}});
                        a.convertToChessboard(a.readFileByFileReader("resource/save1.txt"));
                    }
                }
                //todo:错误处理
                int d=a.getStore().size();
                q=0;
                javax.swing.Timer timer = new javax.swing.Timer(5000, b -> {
                    remove(GameController.getChessboard());
                    if (q!=0){
                    GameController.setChessboard(new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE,a.getStore().get(q),a.getCurrentColor().get(q-1)));}else {
                        GameController.setChessboard(new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE,a.getStore().get(q),a.getCurrentColor().get(q+1)));
                    }
                    GameController.getChessboard().setLocation(0,0);
                    add(GameController.getChessboard());
                    GameController.getChessboard().repaint();
                    qAdd();
                    System.out.println("q");
                    if(q==a.getStore().size()){
                    }
                });
                timer.start();

                for (int i = 0; i < a.getStore().size(); i++) {

                }
            } catch (Exception a) {
                q=0;
                a.printStackTrace();
            }
            switch (GameController.getError()) {
                case 100:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, String.format("ERROR<%d>", GameController.getError()), "error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        });

    }

    /**
     * 添加排行榜
     */
    private void addRankingButton() {
        JButton button = new JButton("Ranking List");
        button.setLocation(HEIGTH, HEIGTH / 10 + 310);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click ranking list");

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

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 370);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            GameController.setError(100);
            JFileChooser fileChooser = new JFileChooser(); // 创建一个文件选择器
            fileChooser.setDialogTitle("选择要打开的文件"); // 给它一个标题
            File currentDirectory = new File("resource"); // 默认目录。TODO：改成你电脑上的目录
            fileChooser.setCurrentDirectory(currentDirectory); // 设置文件选择器起始目录为默认目录
            int result = fileChooser.showOpenDialog(button); // 打开文件选择器
            File chosenFile = null;
            if (result == JFileChooser.APPROVE_OPTION) { // 如果最终确认选择而不是推出
                chosenFile = fileChooser.getSelectedFile(); // 获取选中的文件
            }
            try {
                String path = chosenFile.getAbsolutePath();
                remove(GameController.getChessboard());
                GameController.setChessboard(new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE, path));
                GameController.getChessboard().setLocation(0,0);
                add(GameController.getChessboard());
                GameController.getChessboard().repaint();
            } catch (Exception a) {
                a.printStackTrace();
            }
            switch (GameController.getError()) {
                case 100:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, String.format("ERROR<%d>", GameController.getError()), "error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        });
    }

    private void addResetButton() {
        JButton button = new JButton("Reset");
        button.setLocation(HEIGTH, HEIGTH / 10 + 490);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click reset");
            remove(GameController.getChessboard());
            Save_Write a = new Save_Write();
            a.writeFileByFileWriter("resource/save1.txt", new ArrayList<String>() {{
                add("RNBQKBNRPPPPPPPP________________________________pppppppprnbqkbnrw");
            }});
            a.convertToChessboard(a.readFileByFileReader("resource/save1.txt"));


            GameController.setChessboard(new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE, "resource/save1.txt"));
            GameController.getChessboard().setLocation(0,0);
            add(GameController.getChessboard());
            GameController.getChessboard().repaint();
        });
    }

    public void addRepentButton() {
        JButton button = new JButton("Repent");
        button.setLocation(HEIGTH, HEIGTH / 10 + 550);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click repent");
            remove(GameController.getChessboard());
            GameController.getChessboard().repentChess();
            //todo:异常处理
            String path = "resource/save2.txt";
            Save_Write sW = new Save_Write();
            sW.setStore(GameController.getChessboard().getStore());
            sW.setCurrentColor(GameController.getChessboard().getCurrentColors());
            sW.writeFileByFileWriter(path, sW.convertToList(sW.getStore()));
            GameController.setChessboard(new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE, "resource/save2.txt"));
            GameController.getChessboard().setLocation(0,0);
            add(GameController.getChessboard());
            GameController.getChessboard().repaint();
        });
    }

    /**
     * 增加保存按钮<br>
     */
    private void addSaveButton() {
        JButton button = new JButton("Save");
        button.setLocation(HEIGTH, HEIGTH / 10 + 430);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click save");
            String path = JOptionPane.showInputDialog(this, "Input Path here");
            Save_Write sW = new Save_Write();
            sW.setStore(GameController.getChessboard().getStore());
            sW.setCurrentColor(GameController.getChessboard().getCurrentColors());
            sW.writeFileByFileWriter(path, sW.convertToList(sW.getStore()));
//todo：故障处理
        });
    }
}
