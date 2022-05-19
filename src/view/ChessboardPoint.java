package view;

/**
 * 这个类表示棋盘上的位置，如(0, 0), (0, 7)等等
 * 其中，左上角是(0, 0)，左下角是(7, 0)，右上角是(0, 7)，右下角是(7, 7)
 */
public class ChessboardPoint {
    private int x, y;

    public ChessboardPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "("+x + ","+y+") " + "on the chessboard is clicked!";
    }

    public ChessboardPoint offset(int dx,int dy){
        if(x+dx>=8||x+dx<0){
            return null;
        }
        if(y+dy>=8||y+dy<0){
            return null;
        }
        return new ChessboardPoint(x+dx,y+dy);
    }
    public boolean offset(){
        if(this.getX()>=8 || this.getX()<0){
            return true;
        }
        return this.getY() >= 8 || this.getY() < 0;
    }
}
