package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类表示国际象棋里面的象
 */
public class BishopChessComponent extends ChessComponent {
    /**
     * 黑象和白象的图片，static使得其可以被所有象对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image BISHOP_WHITE;
    private static Image BISHOP_BLACK;
    private static Image white;

    /**
     * 象棋子对象自身的图片，是上面两种中的一种
     */
    private Image BishopImage;
    /**
     * 读取加载象棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (BISHOP_WHITE == null) {
            BISHOP_WHITE = ImageIO.read(new File("./images/Bishop-white.png"));
        }

        if (BISHOP_BLACK == null) {
            BISHOP_BLACK = ImageIO.read(new File("./images/Bishop-black.png"));
        }
        if (white == null) {
            white = ImageIO.read(new File("purewhite/50.png"));
        }
    }
    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定BishopImage的图片是哪一种
     *
     * @param color 棋子颜色
     */
    private void initiateBishopImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                BishopImage = BISHOP_WHITE;
            } else if (color == ChessColor.BLACK) {
                BishopImage = BISHOP_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 构造对象方法，第一行为原方法，第二行为重构方法，将原方法拆分成构造方法和get数据方法
     */
    public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateBishopImage(color);
    }
    public BishopChessComponent(ChessboardPoint chessboardPoint,ChessColor color){
        super(chessboardPoint,color);
    }
    public void MoreInformation(Point location, ChessColor color, ClickController listener, int size) {
        super.MoreInformation(location, color, listener, size);
        initiateBishopImage(color);
    }
    /**
     * 象棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 象棋子移动的合法性
     */
    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
//        ChessboardPoint source = getChessboardPoint();
//        if (source.getX() == destination.getX()) {
//            int row = source.getX();
//            for (int col = Math.min(source.getY(), destination.getY()) + 1;
//                 col < Math.max(source.getY(), destination.getY()); col++) {
//                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
//                    return false;
//                }
//            }
//        } else if (source.getY() == destination.getY()) {
//            int col = source.getY();
//            for (int row = Math.min(source.getX(), destination.getX()) + 1;
//                 row < Math.max(source.getX(), destination.getX()); row++) {
//                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
//                    return false;
//                }
//            }
//        } else { // Not on the same row or the same column.
//            return false;
//        }
     return true;
  }

    @Override
    public List<ChessboardPoint> canMoveTo() {
        int MAS = 8;
        ArrayList<ChessboardPoint> list  = new ArrayList<>();
        Directions directions=new Directions();
        for (ChessboardPoint direct: directions.fork()) {
            for (int i = 1; i <= MAS; i++) {
                ChessboardPoint nextPosition = new ChessboardPoint(this.getSource().getX() + direct.getX() * i, this.getSource().getY() + direct.getY() * i);
                if (!nextPosition.offset()) {
                    if (this.getGame().getChess(nextPosition.getX(), nextPosition.getY()).getChessColor().equals(this.getChessColor())) {
                        break;
                    }
                    if(this.getGame().getChess(nextPosition.getX(), nextPosition.getY()).getChessColor().equals(ChessColor.NONE)) {
                        list.add(nextPosition);
                    } else {
                        list.add(nextPosition);
                        break;
                    }
                }
                else {
                    break;
                }
            }
        }
        return list;
    }

    @Override
    public char toChar(){
        switch (chessColor){
            case WHITE:
                return 'b';
        }
        return 'B';
    }
    /**
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(BishopImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(BishopImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.drawImage(white, 0, 0, getWidth() , getHeight(), this);
        }
    }

    @Override
    public void loadChessGame(List<String> chessboard) {

    }

    @Override
    public ChessColor getCurrentPlayer() {
        return null;
    }

    @Override
    public ChessComponent getChess(int x, int y) {
        return null;
    }

    @Override
    public String getChessboardGraph() {
        return null;
    }

    @Override
    public String getCapturedChess(ChessColor player) {
        return null;
    }

    @Override
    public boolean moveChess(int sourceX, int sourceY, int targetX, int targetY) {
        return false;
    }
}