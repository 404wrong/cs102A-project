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
                    case 'K':
                        a = a + 100;
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
                    case 'k':
                        a = a + 100;
                        break;
                }
            }
        }
        return a;
    }

    /**
     * 被对方的棋子吃
     */
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
                        a = a + 10;
                        break;
                    case 'R':
                        a = a + 10;
                        break;
                }
            }
        } else {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'q':
                        a = a + 10;
                        break;
                    case 'r':
                        a = a + 10;
                        break;
                }
            }
        }
        canMoveTo = knightChessComponent.canMoveTo();
        if (chessColor == ChessColor.WHITE) {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'N':
                        a = a + 10;
                        break;
                }
            }
        } else {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'n':
                        a = a + 10;
                        break;
                }
            }
        }
        canMoveTo = bishopChessComponent.canMoveTo();
        if (chessColor == ChessColor.WHITE) {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'Q':
                        a = a + 10;
                        break;
                    case 'B':
                        a = a + 10;
                        break;
                }
            }
        } else {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'q':
                        a = a + 10;
                        break;
                    case 'b':
                        a = a + 10;
                        break;
                }
            }
        }
        if (chessColor == ChessColor.BLACK) {
            if (chessboardPoint.getX() + 1 >= 0 && chessboardPoint.getX() + 1 < 8 && chessboardPoint.getY() + 1 >= 0 && chessboardPoint.getY() + 1 < 8) {
                if (GameController.getChessboard().getChess(chessboardPoint.getX() + 1, chessboardPoint.getY() + 1).toChar() == 'p') {
                    a = a + 10;
                }
            }
            if (chessboardPoint.getX() + 1 >= 0 && chessboardPoint.getX() + 1 < 8 && chessboardPoint.getY() - 1 >= 0 && chessboardPoint.getY() - 1 < 8) {
                if (GameController.getChessboard().getChess(chessboardPoint.getX() + 1, chessboardPoint.getY() - 1).toChar() == 'p') {
                    a = a + 10;
                }
            }
        } else {
            if (chessboardPoint.getX() - 1 >= 0 && chessboardPoint.getX() - 1 < 8 && chessboardPoint.getY() + 1 >= 0 && chessboardPoint.getY() + 1 < 8) {
                if (GameController.getChessboard().getChess(chessboardPoint.getX() - 1, chessboardPoint.getY() + 1).toChar() == 'P') {
                    a = a + 10;
                }
            }
            if (chessboardPoint.getX() - 1 >= 0 && chessboardPoint.getX() - 1 < 8 && chessboardPoint.getY() - 1 >= 0 && chessboardPoint.getY() - 1 < 8) {
                if (GameController.getChessboard().getChess(chessboardPoint.getX() - 1, chessboardPoint.getY() - 1).toChar() == 'P') {
                    a = a + 10;
                }
            }
        }
        return a;
    }

    /**
     * 被自己的棋子保护
     */
    public static int beLookedBy(ChessComponent chessComponent, ChessColor chessColor) {
        int a = 0;
        ChessboardPoint chessboardPoint = chessComponent.getChessboardPoint();
        ChessComponent rookChessComponent = new RookChessComponent(chessboardPoint, chessColor);
        ChessComponent knightChessComponent = new KnightChessComponent(chessboardPoint, chessColor);
        ChessComponent bishopChessComponent = new BishopChessComponent(chessboardPoint, chessColor);

        List<ChessboardPoint> canMoveTo = rookChessComponent.canMoveTo();
        if (chessColor == ChessColor.BLACK) {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'Q':
                        a = a + 10;
                        break;
                    case 'R':
                        a = a + 10;
                        break;
                }
            }
        } else {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'q':
                        a = a + 10;
                        break;
                    case 'r':
                        a = a + 10;
                        break;
                }
            }
        }
        canMoveTo = knightChessComponent.canMoveTo();
        if (chessColor == ChessColor.BLACK) {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'N':
                        a = a + 10;
                        break;
                }
            }
        } else {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'n':
                        a = a + 10;
                        break;
                }
            }
        }
        canMoveTo = bishopChessComponent.canMoveTo();
        if (chessColor == ChessColor.BLACK) {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'Q':
                        a = a + 10;
                        break;
                    case 'B':
                        a = a + 10;
                        break;
                }
            }
        } else {
            for (int i = 0; i < canMoveTo.size(); i++) {
                switch (GameController.getChessboard().getChess(canMoveTo.get(i)).toChar()) {
                    case 'q':
                        a = a + 10;
                        break;
                    case 'b':
                        a = a + 10;
                        break;
                }
            }
        }
        if (chessColor == ChessColor.WHITE) {
            if (chessboardPoint.getX() + 1 >= 0 && chessboardPoint.getX() + 1 < 8 && chessboardPoint.getY() + 1 >= 0 && chessboardPoint.getY() + 1 < 8) {
                if (GameController.getChessboard().getChess(chessboardPoint.getX() + 1, chessboardPoint.getY() + 1).toChar() == 'p') {
                    a = a + 10;
                }
            }
            if (chessboardPoint.getX() + 1 >= 0 && chessboardPoint.getX() + 1 < 8 && chessboardPoint.getY() - 1 >= 0 && chessboardPoint.getY() - 1 < 8) {
                if (GameController.getChessboard().getChess(chessboardPoint.getX() + 1, chessboardPoint.getY() - 1).toChar() == 'p') {
                    a = a + 10;
                }
            }
        } else {
            if (chessboardPoint.getX() - 1 >= 0 && chessboardPoint.getX() - 1 < 8 && chessboardPoint.getY() + 1 >= 0 && chessboardPoint.getY() + 1 < 8) {
                if (GameController.getChessboard().getChess(chessboardPoint.getX() - 1, chessboardPoint.getY() + 1).toChar() == 'P') {
                    a = a + 10;
                }
            }
            if (chessboardPoint.getX() - 1 >= 0 && chessboardPoint.getX() - 1 < 8 && chessboardPoint.getY() - 1 >= 0 && chessboardPoint.getY() - 1 < 8) {
                if (GameController.getChessboard().getChess(chessboardPoint.getX() - 1, chessboardPoint.getY() - 1).toChar() == 'P') {
                    a = a + 10;
                }
            }
        }
        return a;
    }
}
