package model;

import view.ChessboardPoint;
import controller.ClickController;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类表示棋盘上的空位置
 */
public class EmptySlotComponent extends ChessComponent {

    public EmptySlotComponent(ChessboardPoint chessboardPoint, Point location, ClickController listener, int size) {
        super(chessboardPoint, location, ChessColor.NONE, listener, size);
    }
    public EmptySlotComponent(ChessboardPoint chessboardPoint,ChessColor color){
        super(chessboardPoint,color);
    }
    public void MoreInformation(Point location, ChessColor color, ClickController listener, int size) {
        super.MoreInformation(location, color, listener, size);
    }
    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
            return false;
    }

    @Override
    public List<ChessboardPoint> canMoveTo() {
        return new ArrayList<>();
    }

    @Override
    public void loadResource() throws IOException {
        //No resource!
    }
    @Override
    public char toChar(){
        return '_';
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
