package model;

import view.ChessboardPoint;
import controller.ClickController;

import java.awt.*;
import java.io.IOException;

/**
 * 这个类表示棋盘上的空位置
 */
public class EmptySlotComponent extends ChessComponent {

    public EmptySlotComponent(ChessboardPoint chessboardPoint, Point location, ClickController listener, int size) {
        super(chessboardPoint, location, ChessColor.NONE, listener, size);
    }
    public EmptySlotComponent(ChessboardPoint chessboardPoint){
        super(chessboardPoint);
    }
    public void MoreInformation(Point location, ChessColor color, ClickController listener, int size) {
        super.MoreInformation(location, color, listener, size);
    }
    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        return false;
    }

    @Override
    public void loadResource() throws IOException {
        //No resource!
    }

    @Override
    public char toChar(){
        return '_';
    }

}
