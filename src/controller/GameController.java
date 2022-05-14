package controller;

import view.Chessboard;

public class GameController {
    private static Chessboard chessboard;

    public static Chessboard getChessboard() {
        return chessboard;
    }

    public static void setChessboard(Chessboard cb) {
        chessboard = cb;
    }
}
