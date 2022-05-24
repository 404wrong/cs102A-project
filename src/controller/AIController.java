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
     * 随机行棋
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
    public void AIGO() {
        ArrayList<ChessComponent> blackChess = Chessboard.blackChess;
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
                    ChessComponent chessComponent = new KingChessComponent(canMoveTo.get(j), ChessColor.BLACK);//干掉报错
                    switch (blackChess.get(i).toChar()) {
                        case 'K':
                            chessComponent = new KingChessComponent(canMoveTo.get(j), ChessColor.BLACK);
                            break;
                        case 'Q':
                            chessComponent = new QueenChessComponent(canMoveTo.get(j), ChessColor.BLACK);
                            break;
                        case 'B':
                            chessComponent = new BishopChessComponent(canMoveTo.get(j), ChessColor.BLACK);
                            break;
                        case 'N':
                            chessComponent = new KnightChessComponent(canMoveTo.get(j), ChessColor.BLACK);
                            break;
                        case 'R':
                            chessComponent = new RookChessComponent(canMoveTo.get(j), ChessColor.BLACK);
                            break;
                        case 'P':
                            chessComponent = new PawnChessComponent(canMoveTo.get(j), ChessColor.BLACK);
                            break;
                    }

                    ChessColor chessColor = chessComponent.getChessColor();
                    if (EatenController.beLookedBy(chessComponent, chessColor) - EatenController.beEatenBy(chessComponent, chessColor) >= 0) {
                        if (EatenController.eatOpponent(chessComponent, chessColor) > chessComponent.count) {
                            blackChess.get(i).count = EatenController.eatOpponent(chessComponent, chessColor);
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
        //终点
        return;
    }
//    public void
}
