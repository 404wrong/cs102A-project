package view;

import controller.ClickController;
import controller.GameController;
import model.*;
import save_write.Save_Write;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类表示面板上的棋盘组件对象
 */
public class Chessboard extends JComponent {
    /**
     * CHESSBOARD_SIZE： 棋盘是8 * 8的<br>
     * BACKGROUND_COLORS: 棋盘的两种背景颜色<br>
     * chessListener：棋盘监听棋子的行动<br>
     * chessboard: 表示8 * 8的棋盘<br>
     * currentColor: 当前行棋方
     */
    private static final Icon defaultIcon=new ImageIcon("images/white-0%.png");
    private static final Icon pressedIcon=new ImageIcon("images/white-25%.png");
    private static final Icon rolloverIcon=new ImageIcon("images/white-25%.png");

    private ArrayList<ChessComponent[][]> store;
private ArrayList<ChessColor> currentColors;
    private ChessComponent[][] chessComponents;
    private ChessColor currentColor;
    //all chessComponents in this chessboard are shared only one model controller
    private final ClickController clickController = new ClickController(this);
    private int CHESS_SIZE;

    public void setCHESS_SIZE(int width) {
        this.CHESS_SIZE = width / 8;
    }

    public void addWhiteButton(){
        JButton a=new JButton();
        a.setEnabled(true);
        a.setIcon(defaultIcon);
        a.setPressedIcon(pressedIcon);
        a.setRolloverIcon(rolloverIcon);
        a.setBorderPainted(false);
        a.setContentAreaFilled(false);
        a.setFocusPainted(false);
        a.setMargin(new Insets(0,0,0,0));
        a.setRolloverSelectedIcon(pressedIcon);
        a.setSelectedIcon(defaultIcon);
    }


    /**
     * 该构造方法已重写，如要导入空棋盘请path导入无目标地址或“resource/save1.txt”<br>
     * todo：错误文件识别<br>
     * 在这个构造方法中导入了当前棋盘，全部棋盘（历史棋盘）（通过历史棋盘处理回放）<br>
     * 初始化了：store<br>
     *         chessComponents<br>
     */
    public Chessboard(int width, int height, String path) {
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        CHESS_SIZE = width / 8;
        System.out.printf("chessboard size = %d, chess size = %d\n", width, CHESS_SIZE);

        Save_Write a = new Save_Write();
        if (!a.convertToChessboard(a.readFileByFileReader(path))) {
            if (!a.convertToChessboard(a.readFileByFileReader("resource/save1.txt"))) {
                a.writeFileByFileWriter("resource/save1.txt", new ArrayList<String>() {{add("RNBQKBNRPPPPPPPP________________________________pppppppprnbqkbnrw");}});
                a.convertToChessboard(a.readFileByFileReader("resource/save1.txt"));
            }
        }
        store = a.getStore();
        currentColors=a.getCurrentColor();
        chessComponents = copyChessComponent(store.get(store.size() - 1));
        currentColor=currentColors.get(currentColors.size()-1);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                initOnBoard(row, col, chessComponents[row][col]);
            }
        }

    }

    /**
     * 获取当前棋盘
     */
    public ChessComponent[][] getChessComponents() {
        return chessComponents;
    }

    public void repentChess(){
        store.remove(store.size()-1);
        currentColors.remove(currentColors.size()-1);
    }

    /**
     * 获取当前执棋方颜色
     */
    public ChessColor getCurrentColor() {
        return currentColor;
    }

    /**
     * 移动棋子的具体实现
     * todo：//改变这个方法或内部引用//
     *
     * @param chess1
     * @param chess2
     */
    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        chessComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessComponents[row2][col2] = chess2;

        chess1.repaint();
        chess2.repaint();
        store.add(copyChessComponent(chessComponents));
    }

    /**
     * 换人
     */
    public void swapColor() {
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
        currentColors.add(currentColor);
        GameController.getChessGameFrame().removeGamer();
        GameController.getChessGameFrame().addGamer();
    }

    /**
     * 将棋子加入棋盘
     */
    private void initOnBoard(int row, int col, ChessComponent chessComponent) {
        chessComponent.MoreInformation(calculatePoint(row, col), chessComponent.getChessColor(), clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    /**
     * 将棋子放在棋盘上的具体实现，在上一个方法中实现
     */
    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX(), col = chessComponent.getChessboardPoint().getY();

        if (chessComponents[row][col] != null) {
            remove(chessComponents[row][col]);
        }
        add(chessComponents[row][col] = chessComponent);
    }

    /**
     * 我也不知道是咋实现的所以不要改<br>
     * 画图，在ChessComponent中用于画棋子底色，棋子图片<br>
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * 计算图片应该处在的位置
     */
    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }

    private ChessComponent[][] copyChessComponent(ChessComponent[][] cc) {
        ChessComponent[][] cC = new ChessComponent[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cC[i][j] = cc[i][j];
            }
        }
        return cC;
    }

    public ArrayList<ChessComponent[][]> getStore() {
        return store;
    }

    public void setStore(ArrayList<ChessComponent[][]> store) {
        this.store = store;
    }

    public ArrayList<ChessColor> getCurrentColors() {
        return currentColors;
    }

    public void setCurrentColors(ArrayList<ChessColor> currentColors) {
        this.currentColors = currentColors;
    }
}
