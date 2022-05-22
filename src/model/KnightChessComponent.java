package model;

import controller.ClickController;
import controller.GameController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 这个类表示国际象棋里面的马
 */
public class KnightChessComponent extends ChessComponent {
    /**
     * 黑马和白马的图片，static使得其可以被所有马对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image KNIGHT_WHITE;
    private static Image KNIGHT_BLACK;
    private static Image white;

    /**
     * 马棋子对象自身的图片，是上面两种中的一种
     */
    private Image KnightImage;
    /**
     * 读取加载马棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (KNIGHT_WHITE == null) {
            KNIGHT_WHITE = ImageIO.read(new File("./images/Knight-white.png"));
        }

        if (KNIGHT_BLACK == null) {
            KNIGHT_BLACK = ImageIO.read(new File("./images/Knight-black.png"));
        }
        if (white == null) {
            white = ImageIO.read(new File("purewhite/50.png"));
        }
    }
    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定KnightImage的图片是哪一种
     *
     * @param color 棋子颜色
     */
    private void initiateKnightImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                KnightImage = KNIGHT_WHITE;
            } else if (color == ChessColor.BLACK) {
                KnightImage = KNIGHT_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 构造对象方法，第一行为原方法，第二行为重构方法，将原方法拆分成构造方法和get数据方法
     */
    public KnightChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateKnightImage(color);
        this.Type = ChessType.Knight;
    }
    public KnightChessComponent(ChessboardPoint chessboardPoint,ChessColor color){
        super(chessboardPoint,color);
        this.Type = ChessType.Knight;

    }
    public void MoreInformation(Point location, ChessColor color, ClickController listener, int size) {
        super.MoreInformation(location, color, listener, size);
        initiateKnightImage(color);
    }


    @Override
    public boolean canMoveTo(ChessboardPoint destination) {
        for (ChessboardPoint p:GameController.getChessboard().getChess(getChessboardPoint().getX(),getChessboardPoint().getY()).canMoveTo()) {
            if(p.getX() == destination.getX() && p.getY() == destination.getY()){
                return true;
            }
        }
        return false;
    }


    @Override
    public List<ChessboardPoint> canMoveTo() {
        int MAS = 1;
        ArrayList<ChessboardPoint> list = new ArrayList<>();
        Directions directions=new Directions();
        for (ChessboardPoint direct: directions.knight()) {
            for (int i = 1; i <= MAS; i++) {
                ChessboardPoint nextPosition = new ChessboardPoint(this.getChessboardPoint().getX() + direct.getX() * i, this.getChessboardPoint().getY() + direct.getY() * i);
                if (!nextPosition.offset()) {
                    if (GameController.getChessboard().getChess(nextPosition.getX(), nextPosition.getY()).getChessColor().equals(this.getChessColor())) {
                        break;
                    }
                    if(GameController.getChessboard().getChess(nextPosition.getX(), nextPosition.getY()).getChessColor().equals(ChessColor.NONE)) {
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
        for (ChessboardPoint i : list) {
            System.out.println(i);
        }
        return list;
    }

    @Override
    public char toChar(){
        switch (chessColor){
            case WHITE:
                return 'n';
        }
        return 'N';
    }
    /*
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(KnightImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(KnightImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.drawImage(white, 0, 0, getWidth() , getHeight(), this);
        }
    }
}





