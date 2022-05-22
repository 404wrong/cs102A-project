package view;

import addModel.TransparentButton;
import controller.ClickController;
import controller.GameController;
import model.*;
import save_write.Save_Write;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 这个类表示面板上的棋盘组件对象
 */
public class Chessboard extends JComponent {
    public static ArrayList<ChessComponent> whiteChess;
    public static ArrayList<ChessComponent> blackChess;
    public static ArrayList<ChessComponent> allChess;
    public static ArrayList<PawnChessComponent> Pawns;

    /**
     * CHESSBOARD_SIZE： 棋盘是8 * 8的<br>
     * BACKGROUND_COLORS: 棋盘的两种背景颜色<br>
     * chessListener：棋盘监听棋子的行动<br>
     * chessboard: 表示8 * 8的棋盘<br>
     * currentColor: 当前行棋方
     */
    private static final Icon defaultIcon = new ImageIcon("images/white-0%.png");
    private static final Icon pressedIcon = new ImageIcon("images/white-25%.png");
    private static final Icon rolloverIcon = new ImageIcon("images/white-25%.png");

    long midTime;
    private ArrayList<ChessComponent[][]> store = new ArrayList<>();
    private ArrayList<ChessColor> currentColors = new ArrayList<>();
    private ChessComponent[][] chessComponents;
    private ChessColor currentColor;
    //all chessComponents in this chessboard are shared only one model controller
    private final ClickController clickController = new ClickController(this);
    private int CHESS_SIZE;
    public JLabel timerLabel;
    int a;

    public void setCHESS_SIZE(int width) {
        this.CHESS_SIZE = width / 8;
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
        CHESS_SIZE = width / 8;
        setLocation(0, 0);
        setSize(width + CHESS_SIZE * 2, height);
        System.out.printf("chessboard size = %d, chess size = %d\n", width, CHESS_SIZE);

        Save_Write a = new Save_Write();
        if (!a.convertToChessboard(a.readFileByFileReader(path))) {
            if (!a.convertToChessboard(a.readFileByFileReader("resource/save1.txt"))) {
                a.writeFileByFileWriter("resource/save1.txt", new ArrayList<String>() {{
                    add("RNBQKBNRPPPPPPPP________________________________pppppppprnbqkbnrw");
                }});
                a.convertToChessboard(a.readFileByFileReader("resource/save1.txt"));
            }
        }
        store = a.getStore();
        currentColors = a.getCurrentColor();
        chessComponents = copyChessComponent(store.get(store.size() - 1));
        currentColor = currentColors.get(currentColors.size() - 1);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                initOnBoard(row, col, chessComponents[row][col]);
            }
        }
        GameController.getChessGameFrame().gamer.setText("Current  " + getCurrentColor().toString());
        GameController.getChessGameFrame().gamer.repaint();
        addTimerLabel();
    }

    public Chessboard(int width, int height, ChessComponent[][] chessComponent, ChessColor currentColor) {
        JButton jb = new TransparentButton(0, 0, 0, 0);
        jb.setLocation(0, 0);
        jb.setSize(width, width);
        add(jb);

        setLayout(null); // Use absolute layout.
        CHESS_SIZE = width / 8;
        setLocation(0, 0);
        setSize(width + CHESS_SIZE * 2, height);
        System.out.printf("chessboard size = %d, chess size = %d\n", width, CHESS_SIZE);

        this.currentColor = currentColor;
        this.chessComponents = chessComponent;
        store.add(chessComponents);
        currentColors.add(currentColor);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                initOnBoard(row, col, chessComponents[row][col]);
            }
        }
        GameController.getChessGameFrame().gamer.setText("Current  " + GameController.getChessboard().getCurrentColor().toString());
        GameController.getChessGameFrame().gamer.repaint();
    }

    /**
     * 获取当前棋盘
     */
    public ChessComponent[][] getChessComponents() {
        return chessComponents;
    }

    public void repentChess() {
        store.remove(store.size() - 1);
        currentColors.remove(currentColors.size() - 1);
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

        //兵的奇怪规则
        if (chess1.getType().equals(ChessComponent.ChessType.Pawn)) {
            PawnChessComponent pawn = (PawnChessComponent) chess1;
            //可以被创死
            if (pawn.isFirstMove()) {
                pawn.setFirstMove(false);
                if (pawn.getChessboardPoint().getX() == 3 || pawn.getChessboardPoint().getX() == 4) {
                    pawn.setChuang(true);
                }
                System.out.println(pawn.getChuang());

            }
            //创死过路的
            ChessComponent back = getChess(pawn.getChessboardPoint().getX()+(-1* pawn.getDirect().getX()),pawn.getChessboardPoint().getY());
            if (back.getType().equals(ChessComponent.ChessType.Pawn)) {
                PawnChessComponent bastard = (PawnChessComponent) back;
                if (bastard.getChuang()) {
                    remove(bastard);
                    ChessComponent ChuangSi = new EmptySlotComponent(bastard.getChessboardPoint(), bastard.getLocation(), clickController, CHESS_SIZE);
                    chessComponents[pawn.getChessboardPoint().getX() - pawn.getDirect().getX()][pawn.getChessboardPoint().getY()] = ChuangSi;
                    add(ChuangSi);
                    ChuangSi.repaint();
                }
            }
            pawn.repaint();
        }

        //第一步判断
        if (chess1.isFirstMove()) {
            chess1.setFirstMove(false);
        }

        //王车易位判断
        if (chess1.getType().equals(ChessComponent.ChessType.King)) {
            if (col1 - col2 == 2) {
                switchRookToKing(0);
            }
            else if (col1 - col2 == -2) {
                switchRookToKing(1);
            }
        }

        chess1.repaint();
        chess2.repaint();
        store.add(copyChessComponent(chessComponents));
    }

    /**
     * 换人
     */
    public void swapColor() {
        eventCheckBefore();
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
        currentColors.add(currentColor);
        GameController.getChessGameFrame().gamer.setText("Current  " + GameController.getChessboard().getCurrentColors().get(GameController.getChessboard().getCurrentColors().size() - 1).toString());
        GameController.getChessGameFrame().gamer.repaint();
        a = 20;
        eventCheckAfter();
    }

    /**
     * 将棋子加入棋盘
     */
    public void initOnBoard(int row, int col, ChessComponent chessComponent) {
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

    public void addTimerLabel() {
        a = 20;
        timerLabel = new JLabel(String.format("Time left: %d", a));
        timerLabel.setLocation(CHESS_SIZE * 8, CHESS_SIZE * 2);
        timerLabel.setSize(CHESS_SIZE * 2, CHESS_SIZE);
        timerLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(timerLabel);
        javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {
            changeTimerLabel();
            if (a == 0) {
                swapColor();
            }
        });
        timer.start();
    }

    public void changeTimerLabel() {
        a--;
        timerLabel.setText(String.format("Time left: %d", a));
    }

    public ChessComponent getChess(ChessboardPoint point) {
        return chessComponents[point.getX()][point.getY()];
    }

    public ChessComponent getChess(int x, int y) {
        return getChess(new ChessboardPoint(x, y));
    }

    public void setCurrentColor(ChessColor currentColor) {
        this.currentColor = currentColor;
    }

    public void updateLists() {
        whiteChess = new ArrayList<>();
        blackChess = new ArrayList<>();
        allChess = new ArrayList<>();
        Pawns = new ArrayList<>();
        for (int c = 0; c < chessComponents.length; c++ ) {
            for (int r = 0; r < chessComponents[0].length; r++ ) {
                ChessComponent chess = getChess(c,r);
                if (chess.getChessColor().equals(ChessColor.BLACK)) {
                    blackChess.add(chess);
                    allChess.add(chess);
                } else if (chess.getChessColor().equals(ChessColor.WHITE)){
                    whiteChess.add(chess);
                    allChess.add(chess);
                }
                if (chess.getType().equals(ChessComponent.ChessType.Pawn)) {
                    Pawns.add((PawnChessComponent) chess);
                }
            }
        }
    }

    public void isChecked() {

    }

    public void cantChuang() {
        //在换边之后
        for (PawnChessComponent p : Pawns) {
            if (p.getChessColor().equals(currentColor) && p.getChuang()) {
                p.setChuang(false);
            }
        }
    }

    public ChessComponent getSpecificChess(ChessColor player, ChessComponent.ChessType chess) {
        for (ChessComponent p : allChess) {
            if (p.getChessColor().equals(player) && p.getType().equals(chess)) {
                return p;
            }
        }
        return null;
    }

    public void eventCheckBefore() {
        updateLists();
        checkAwesomePawn();
    }

    public void eventCheckAfter() {
        cantChuang();
    }

    public boolean canSwitchKR(int i) {
        RookChessComponent rook = null;
        KingChessComponent king = null;
        if (currentColor.equals(ChessColor.BLACK)) {
            for (ChessComponent p : blackChess) {
                if (p.getType().equals(ChessComponent.ChessType.Rook)) {
                    RookChessComponent r = (RookChessComponent) p;
                    if (r.getID()==i) {
                        rook = r;
                    }
                }
                if (p.getType().equals(ChessComponent.ChessType.King)) {
                    king = (KingChessComponent) p;
                }
            }
            if (rook!=null&& king != null) {
                if (!rook.isFirstMove() || !king.isFirstMove()) {
                    return false;
                }
            }
        }
        if (currentColor.equals(ChessColor.WHITE)) {
            for (ChessComponent p : whiteChess) {
                if (p.getType().equals(ChessComponent.ChessType.Rook)) {
                    RookChessComponent r = (RookChessComponent) p;
                    if (r.getID()==i) {
                        rook = r;
                    }
                }
                if (p.getType().equals(ChessComponent.ChessType.King)) {
                    king = (KingChessComponent) p;
                }
            }
            if (rook!=null&& king != null) {
                if (!rook.isFirstMove() || !king.isFirstMove()) {
                    return false;
                }
            }
        }
        //换左车
        if (i == 1) {
            for (int index = 1; index < 4; index++) {
                if (!getChess(king.getChessboardPoint().getX(), king.getChessboardPoint().getY()- index).getType().equals(ChessComponent.ChessType.Empty)) {
                    return false;
                }
            }
        }
        //换右车
        else if (i == 0){
            for (int index = 1; index < 3; index++) {
                if (!getChess(king.getChessboardPoint().getX(),king.getChessboardPoint().getY()+index).getType().equals(ChessComponent.ChessType.Empty)){
                    return false;
                }
            }
        }
        return true;
    }

    public void switchRookToKing(int i) {
        KingChessComponent king = null;
        if (currentColor.equals(ChessColor.BLACK)) {
            for (ChessComponent p : blackChess) {
                if (p.getType().equals(ChessComponent.ChessType.King)) {
                    king = (KingChessComponent) p;
                }
            }

        }
        if (currentColor.equals(ChessColor.WHITE)) {
            for (ChessComponent p : whiteChess) {
                if (p.getType().equals(ChessComponent.ChessType.King)) {
                    king = (KingChessComponent) p;
                }
            }

        }
        //换左车
        RookChessComponent rook = null;
        if (i == 1) {
            rook = (RookChessComponent) getChess(king.getChessboardPoint().getX(), king.getChessboardPoint().getY()-2);
            swapChessComponents(rook,getChess(king.getChessboardPoint().getX(), king.getChessboardPoint().getY()+1));
        }
        //换右车
        else if (i == 0){
            rook = (RookChessComponent) getChess(king.getChessboardPoint().getX(), king.getChessboardPoint().getY()+1);
            swapChessComponents(rook,getChess(king.getChessboardPoint().getX(), king.getChessboardPoint().getY()-1));
        }
        rook.repaint();
    }

    //检查能兵变
    public void checkAwesomePawn() {
        //在这里写兵变面板，默认是变车，对应变啥写兵里面了
        for (PawnChessComponent p : Pawns) {
            if (p.getChessColor().equals(ChessColor.WHITE) && p.getChessboardPoint().getX() == 0) {
                p.balalaPawn(0);

                return;
            }
            if (p.getChessColor().equals(ChessColor.BLACK) && p.getChessboardPoint().getX() == 7) {
                p.balalaPawn(0);

                return;
            }
        }
    }




}
