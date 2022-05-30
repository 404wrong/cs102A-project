package controller;

import model.*;
import view.Chessboard;
import view.ChessboardPoint;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AIController {
    /**
     * AI执黑子
     */
    int difficulty=0;
    public AIController(int difficulty) {
        this.difficulty=difficulty;
        GameController.aiController = this;
    }

    /**
     * 区分难度
     */
    public void AIDifficulties(){
        switch (difficulty){
            case 0:
                randomGo();
                break;
            case 1:
                greedGO();
                break;
            case 2:
                AIGO();
                break;
        }
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
    /**
     * 先吃大的再吃小的
     */
    public void greedGO(){
        ArrayList<ChessComponent> blackChess = Chessboard.blackChess;
        for (int i = 0; i < blackChess.size(); i++) {
            List<ChessboardPoint> canMoveTo = blackChess.get(i).canMoveTo();
            for (int j = 0; j < canMoveTo.size(); j++) {
                if((GameController.getChessboard().getChess(canMoveTo.get(j)).toChar()=='k')){
                    GameController.clickController.onClick(blackChess.get(i));
                    //起点
                    GameController.clickController.onClick(GameController.getChessboard().getChess(canMoveTo.get(j)));
                    //终点
                    return;
                }
            }
        }
        for (int i = 0; i < blackChess.size(); i++) {
            List<ChessboardPoint> canMoveTo = blackChess.get(i).canMoveTo();
            for (int j = 0; j < canMoveTo.size(); j++) {
                if((GameController.getChessboard().getChess(canMoveTo.get(j)).toChar()=='q')){
                    GameController.clickController.onClick(blackChess.get(i));
                    //起点
                    GameController.clickController.onClick(GameController.getChessboard().getChess(canMoveTo.get(j)));
                    //终点
                    return;
                }
            }
        }
        for (int i = 0; i < blackChess.size(); i++) {
            List<ChessboardPoint> canMoveTo = blackChess.get(i).canMoveTo();
            for (int j = 0; j < canMoveTo.size(); j++) {
                if((GameController.getChessboard().getChess(canMoveTo.get(j)).toChar()=='r')){
                    GameController.clickController.onClick(blackChess.get(i));
                    //起点
                    GameController.clickController.onClick(GameController.getChessboard().getChess(canMoveTo.get(j)));
                    //终点
                    return;
                }
            }
        }
        for (int i = 0; i < blackChess.size(); i++) {
            List<ChessboardPoint> canMoveTo = blackChess.get(i).canMoveTo();
            for (int j = 0; j < canMoveTo.size(); j++) {
                if((GameController.getChessboard().getChess(canMoveTo.get(j)).toChar()=='n')){
                    GameController.clickController.onClick(blackChess.get(i));
                    //起点
                    GameController.clickController.onClick(GameController.getChessboard().getChess(canMoveTo.get(j)));
                    //终点
                    return;
                }
            }
        }
        for (int i = 0; i < blackChess.size(); i++) {
            List<ChessboardPoint> canMoveTo = blackChess.get(i).canMoveTo();
            for (int j = 0; j < canMoveTo.size(); j++) {
                if((GameController.getChessboard().getChess(canMoveTo.get(j)).toChar()=='b')){
                    GameController.clickController.onClick(blackChess.get(i));
                    //起点
                    GameController.clickController.onClick(GameController.getChessboard().getChess(canMoveTo.get(j)));
                    //终点
                    return;
                }
            }
        }
        for (int i = 0; i < blackChess.size(); i++) {
            List<ChessboardPoint> canMoveTo = blackChess.get(i).canMoveTo();
            for (int j = 0; j < canMoveTo.size(); j++) {
                if((GameController.getChessboard().getChess(canMoveTo.get(j)).toChar()=='p')){
                    GameController.clickController.onClick(blackChess.get(i));
                    //起点
                    GameController.clickController.onClick(GameController.getChessboard().getChess(canMoveTo.get(j)));
                    //终点
                    return;
                }
            }
        }
        randomGo();
    }
    /**
     * 能吃先吃，不能吃对每一个棋子进行分数排序
     */
    public void AIGO() {
        ArrayList<ChessComponent> blackChess = Chessboard.blackChess;
        //和greedgo内部相似
        for (int i = 0; i < blackChess.size(); i++) {
            List<ChessboardPoint> canMoveTo = blackChess.get(i).canMoveTo();
            for (int j = 0; j < canMoveTo.size(); j++) {
                if ((GameController.getChessboard().getChess(canMoveTo.get(j)).toChar() == 'k')) {
                    GameController.clickController.onClick(blackChess.get(i));
                    //起点
                    GameController.clickController.onClick(GameController.getChessboard().getChess(canMoveTo.get(j)));
                    //终点
                    return;
                }
            }
        }
        for (int i = 0; i < blackChess.size(); i++) {
            List<ChessboardPoint> canMoveTo = blackChess.get(i).canMoveTo();
            for (int j = 0; j < canMoveTo.size(); j++) {
                if ((GameController.getChessboard().getChess(canMoveTo.get(j)).toChar() == 'q')) {
                    GameController.clickController.onClick(blackChess.get(i));
                    //起点
                    GameController.clickController.onClick(GameController.getChessboard().getChess(canMoveTo.get(j)));
                    //终点
                    return;
                }
            }
        }
        for (int i = 0; i < blackChess.size(); i++) {
            List<ChessboardPoint> canMoveTo = blackChess.get(i).canMoveTo();
            for (int j = 0; j < canMoveTo.size(); j++) {
                if ((GameController.getChessboard().getChess(canMoveTo.get(j)).toChar() == 'r')) {
                    GameController.clickController.onClick(blackChess.get(i));
                    //起点
                    GameController.clickController.onClick(GameController.getChessboard().getChess(canMoveTo.get(j)));
                    //终点
                    return;
                }
            }
        }
        for (int i = 0; i < blackChess.size(); i++) {
            List<ChessboardPoint> canMoveTo = blackChess.get(i).canMoveTo();
            for (int j = 0; j < canMoveTo.size(); j++) {
                if ((GameController.getChessboard().getChess(canMoveTo.get(j)).toChar() == 'n')) {
                    GameController.clickController.onClick(blackChess.get(i));
                    //起点
                    GameController.clickController.onClick(GameController.getChessboard().getChess(canMoveTo.get(j)));
                    //终点
                    return;
                }
            }
        }
        for (int i = 0; i < blackChess.size(); i++) {
            List<ChessboardPoint> canMoveTo = blackChess.get(i).canMoveTo();
            for (int j = 0; j < canMoveTo.size(); j++) {
                if ((GameController.getChessboard().getChess(canMoveTo.get(j)).toChar() == 'b')) {
                    GameController.clickController.onClick(blackChess.get(i));
                    //起点
                    GameController.clickController.onClick(GameController.getChessboard().getChess(canMoveTo.get(j)));
                    //终点
                    return;
                }
            }
        }
        for (int i = 0; i < blackChess.size(); i++) {
            List<ChessboardPoint> canMoveTo = blackChess.get(i).canMoveTo();
            for (int j = 0; j < canMoveTo.size(); j++) {
                if((GameController.getChessboard().getChess(canMoveTo.get(j)).toChar()=='p')){
                    GameController.clickController.onClick(blackChess.get(i));
                    //起点
                    GameController.clickController.onClick(GameController.getChessboard().getChess(canMoveTo.get(j)));
                    //终点
                    return;
                }
            }
        }

        for (int i = 0; i < blackChess.size(); i++) {
            List<ChessboardPoint> canMoveTo=blackChess.get(i).canMoveTo();
            if (canMoveTo.size()==0){
                blackChess.remove(i);
            }else {
                for (int j = 0; j < canMoveTo.size(); j++) {
                    new KingChessComponent(canMoveTo.get(j), ChessColor.BLACK);
                    ChessComponent chessComponent = switch (blackChess.get(i).toChar()) {
                        case 'K' -> new KingChessComponent(canMoveTo.get(j), ChessColor.BLACK);
                        case 'Q' -> new QueenChessComponent(canMoveTo.get(j), ChessColor.BLACK);
                        case 'B' -> new BishopChessComponent(canMoveTo.get(j), ChessColor.BLACK);
                        case 'N' -> new KnightChessComponent(canMoveTo.get(j), ChessColor.BLACK);
                        case 'R' -> new RookChessComponent(canMoveTo.get(j), ChessColor.BLACK);
                        case 'P' -> new PawnChessComponent(canMoveTo.get(j), ChessColor.BLACK);
                        default -> new KingChessComponent(canMoveTo.get(j), ChessColor.BLACK);
                    };

                    ChessColor chessColor = chessComponent.getChessColor();
                    if (EatenController.beLookedBy(chessComponent, chessColor) - EatenController.beEatenBy(chessComponent, chessColor) >= 0) {
                        if (EatenController.eatOpponent(chessComponent, chessColor)+ EatenController.beLookedBy(chessComponent, chessColor) - EatenController.beEatenBy(chessComponent, chessColor)> chessComponent.count) {
                            blackChess.get(i).count = EatenController.eatOpponent(chessComponent, chessColor)+EatenController.beLookedBy(chessComponent, chessColor) - EatenController.beEatenBy(chessComponent, chessColor);
                            blackChess.get(i).chessboardPointTo = chessComponent.getChessboardPoint();
                        }
                    } else if (blackChess.get(i).count < 0) {
                        chessComponent.count = -1000;
                    }
                }
            }
        }
        blackChess.sort(new Comparator<ChessComponent>(){
            @Override
            public int compare(ChessComponent o1,ChessComponent o2){
                if(o1.count>=o2.count){
                    return -1;
                }else {
                    return 1;
                }
            }
        });
        GameController.clickController.onClick(blackChess.get(0));
        //起点
        GameController.clickController.onClick(GameController.getChessboard().getChess(blackChess.get(0).chessboardPointTo));
        //重置AI内部评分
        try {
            for (int i = 0; i < blackChess.size(); i++) {
            blackChess.get(i).count=-1;
            blackChess.get(i).chessboardPointTo=blackChess.get(i).canMoveTo().get(0);
        }}catch(Exception e){}
        //终点
        return;
    }
//    public void
}
