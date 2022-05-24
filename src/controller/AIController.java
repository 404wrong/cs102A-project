package controller;

import model.ChessComponent;
import view.Chessboard;
import view.ChessboardPoint;

import java.util.ArrayList;
import java.util.List;

public class AIController {
    /**
     * AI执黑子
     */
    public AIController(int difficulty) {
        GameController.aiController = this;
    }

    /**
     * 随机行棋
     */
    public void randomGo() {
        ArrayList<ChessComponent> blackChess = Chessboard.blackChess;

        int ran = (int) (blackChess.size()*Math.random());
        for (int i = 0; i < blackChess.size(); i++) {
            ChessComponent thisChessComponent = blackChess.get((ran + i) % blackChess.size());
            List<ChessboardPoint> canMoveTo = thisChessComponent.canMoveTo();
            if(canMoveTo.size()!=0){
                GameController.clickController.onClick(thisChessComponent);
                //起点
                GameController.clickController.onClick(GameController.getChessboard().getChess(canMoveTo.get((int)(Math.random()* canMoveTo.size()))));
                //终点
                return;
            }
        }
    }

//    public void
}
