package controller;

import model.*;
import view.ChessboardPoint;

import java.util.List;

public class EatenController {
    public static int eatOpponent(ChessComponent chessComponent, ChessColor chessColor) {
        int a = 0;
        List<ChessboardPoint> canMoveTo = chessComponent.canMoveTo();
        if (chessColor == ChessColor.WHITE) {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'Q':
                        a = a + 9;
                        break;
                    case 'R':
                        a = a + 5;
                        break;
                    case 'N':
                        a = a + 4;
                        break;
                    case 'B':
                        a = a + 3;
                        break;
                    case 'P':
                        a = a + 1;
                        break;
                }
            }
        } else {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'q':
                        a = a + 9;
                        break;
                    case 'r':
                        a = a + 5;
                        break;
                    case 'n':
                        a = a + 4;
                        break;
                    case 'b':
                        a = a + 3;
                        break;
                    case 'p':
                        a = a + 1;
                        break;
                }
            }
        }
        return a;
    }

    public static int beEatenBy(ChessComponent chessComponent, ChessColor chessColor) {
        int a = 0;
        ChessboardPoint chessboardPoint = chessComponent.getChessboardPoint();
        ChessComponent rookChessComponent = new RookChessComponent(chessboardPoint, chessColor);
        ChessComponent knightChessComponent = new KnightChessComponent(chessboardPoint, chessColor);
        ChessComponent bishopChessComponent = new BishopChessComponent(chessboardPoint, chessColor);

        List<ChessboardPoint> canMoveTo = rookChessComponent.canMoveTo();
        if (chessColor == ChessColor.WHITE) {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'Q':
                        a = a + 9;
                        break;
                    case 'R':
                        a = a + 5;
                        break;
                }
            }
        } else {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'q':
                        a = a + 9;
                        break;
                    case 'r':
                        a = a + 5;
                        break;
                }
            }
        }
        canMoveTo = knightChessComponent.canMoveTo();
        if (chessColor == ChessColor.WHITE) {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'N':
                        a = a + 4;
                        break;
                }
            }
        } else {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'n':
                        a = a + 4;
                        break;
                }
            }
        }
        canMoveTo = bishopChessComponent.canMoveTo();
        if (chessColor == ChessColor.WHITE) {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'Q':
                        a = a + 9;
                        break;
                    case 'B':
                        a = a + 3;
                        break;
                }
            }
        } else {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'q':
                        a = a + 9;
                        break;
                    case 'b':
                        a = a + 3;
                        break;
                }
            }
        }
        if (chessColor == ChessColor.WHITE) {
            if (GameController.getChessboard().getChess(chessboardPoint.getX() - 1, chessboardPoint.getY() + 1).toChar() == 'P') {
                a = a + 1;
            }
            if (GameController.getChessboard().getChess(chessboardPoint.getX() - 1, chessboardPoint.getY() - 1).toChar() == 'P') {
                a = a + 1;
            }
        } else {
            if (GameController.getChessboard().getChess(chessboardPoint.getX() - 1, chessboardPoint.getY() + 1).toChar() == 'p') {
                a = a + 1;
            }
            if (GameController.getChessboard().getChess(chessboardPoint.getX() - 1, chessboardPoint.getY() - 1).toChar() == 'p') {
                a = a + 1;
            }
        }
        return a;
    }
}
