package controller;


import addModel.Play0;
import model.ChessComponent;
import view.Chessboard;

public class ClickController {
    private final Chessboard chessboard;
    private ChessComponent first;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
        GameController.clickController=this;
    }

    public void onClick(ChessComponent chessComponent) {
        if (GameController.nullClick==1){
            return;
        }
        new Play0("resource/落子.mp3").start();
        if (first == null) {
            if (handleFirst(chessComponent)) {
                chessComponent.setSelected(true);
                first = chessComponent;
                first.repaint();
            }
        } else {
            if (first == chessComponent) { // 再次点击取消选取
                chessComponent.setSelected(false);
                ChessComponent recordFirst = first;
                first = null;
                recordFirst.repaint();
            } else {
                if (handleSecond(chessComponent)) {
                    //repaint in swap chess method.
                    chessboard.swapChessComponents(first, chessComponent);
                    first.setSelected(false);
                    first = null;
                    chessboard.swapColor();
                } else if (first.getChessColor().equals(GameController.getChessboard().getCurrentColor())) {
                    first.setSelected(false);
                    ChessComponent recordFirst = first;
                    first = null;
                    recordFirst.repaint();
                    if (handleFirst(chessComponent)) {
                        chessComponent.setSelected(true);
                        first = chessComponent;
                        first.repaint();
                    }
                } else {
                    first.setSelected(false);
                    ChessComponent recordFirst = first;
                    first = null;
                    recordFirst.repaint();
                }
            }
        }
    }


    public void setClick(){
        first.setSelected(false);
        ChessComponent recordFirst = first;
        first = null;
        recordFirst.repaint();
    }

    /**
     * @param chessComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param chessComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessComponent.getChessboardPoint());
    }
}
