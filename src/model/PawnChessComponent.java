package model;

import controller.ClickController;
import controller.GameController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类表示国际象棋里面的兵
 */
public class PawnChessComponent extends ChessComponent {
    /**
     * 黑兵和白兵的图片，static使得其可以被所有兵对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image PAWN_WHITE;
    private static Image PAWN_BLACK;
    private static Image white;
    /**
     * 兵棋子对象自身的图片，是上面两种中的一种
     */
    private Image PawnImage;
    /**
     * 读取加载兵棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (PAWN_WHITE == null) {
            PAWN_WHITE = ImageIO.read(new File("./images/Pawn-white.png"));
        }

        if (PAWN_BLACK == null) {
            PAWN_BLACK = ImageIO.read(new File("./images/Pawn-black.png"));
        }
        if (white == null) {
            white = ImageIO.read(new File("purewhite/50.png"));
        }
    }
    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定PawnImage的图片是哪一种
     *
     * @param color 棋子颜色
     */
    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                PawnImage = PAWN_WHITE;
            } else if (color == ChessColor.BLACK) {
                PawnImage = PAWN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 构造对象方法，第一行为原方法，第二行为重构方法，将原方法拆分成构造方法和get数据方法
     */
    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiatePawnImage(color);
    }
    public PawnChessComponent(ChessboardPoint chessboardPoint,ChessColor color){
        super(chessboardPoint,color);
    }
    public void MoreInformation(Point location, ChessColor color, ClickController listener, int size) {
        super.MoreInformation(location, color, listener, size);
        initiatePawnImage(color);
    }
    /**
     * 兵棋子的移动规则
     *
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 兵棋子移动的合法性
     */
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
        int MAS;
        boolean firstMove = true;
        ArrayList<ChessboardPoint> list  = new ArrayList<>();
        ChessboardPoint direct;
        Directions directions=new Directions();
        if (this.getChessColor().equals(ChessColor.WHITE)) {
            direct = directions.up();
        } else {
            direct = directions.down();
        }
        ArrayList<ChessboardPoint> eatAble = new ArrayList<>();
        eatAble.add(new ChessboardPoint(direct.getX(),direct.getY()+1));
        eatAble.add(new ChessboardPoint(direct.getX(),direct.getY()-1));
        if (firstMove) {
            MAS= 2;
            for (int i = 1; i <= MAS; i++) {
                ChessboardPoint nextPosition = new ChessboardPoint(this.getChessboardPoint().getX() + direct.getX() * i, this.getChessboardPoint().getY());

                if (!nextPosition.offset()) {
                    if (GameController.getChessboard().getChess(nextPosition.getX(), nextPosition.getY()).getChessColor().equals(this.getChessColor())) {
                        break;
                    }
                    if(GameController.getChessboard().getChess(nextPosition.getX(), nextPosition.getY()).getChessColor().equals(ChessColor.NONE)) {
                        list.add(nextPosition);
                    } else {
                        break;
                    }
                }
                else {
                    break;
                }
            }
            firstMove = false;
        } else {
            MAS = 1;
            for (int i = 1; i <= MAS; i++) {
                ChessboardPoint nextPosition = new ChessboardPoint(this.getChessboardPoint().getX() + direct.getX() * i, this.getChessboardPoint().getY());
                if (!nextPosition.offset()) {
                    if (GameController.getChessboard().getChess(nextPosition.getX(), nextPosition.getY()).getChessColor().equals(this.getChessColor())) {
                        break;
                    }
                    if(GameController.getChessboard().getChess(nextPosition.getX(), nextPosition.getY()).getChessColor().equals(ChessColor.NONE)) {
                        list.add(nextPosition);
                    } else {
                        break;
                    }
                }
                else {
                    break;
                }
            }

        }
        MAS = 1;
        for (ChessboardPoint dire : eatAble) {
            for (int i = 1; i <= MAS; i++) {
                ChessboardPoint nextPosition = new ChessboardPoint(this.getChessboardPoint().getX() + dire.getX() * i, this.getChessboardPoint().getY() + dire.getY() * i);
                if (!nextPosition.offset()) {
                    if (!GameController.getChessboard().getChess(nextPosition.getX(), nextPosition.getY()).getChessColor().equals(this.getChessColor()) && !GameController.getChessboard().getChess(nextPosition.getX(), nextPosition.getY()).getChessColor().equals(ChessColor.NONE)) {
                        list.add(nextPosition);
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
        }

        return list;
    }

    @Override
    public char toChar(){
        switch (chessColor){
            case WHITE:
                return 'p';
        }
        return 'P';
    }
    /**
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(PawnImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(PawnImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.drawImage(white, 0, 0, getWidth() , getHeight(), this);
        }
    }



}

