package view;


import model.ChessColor;
import model.PawnChessComponent;

import javax.swing.*;
import java.awt.*;

import static view.Chessboard.Pawns;

public class UP extends JFrame {
    private JButton queen,rook,knight,bishop;

    public UP(Chessboard chessboard){
        super("Flow Layout");
        setLayout(new FlowLayout());
        queen =new JButton("Queen");add(queen);
        rook =new JButton("Rook");add(rook);
        knight =new JButton("Knight");add(knight);
        bishop =new JButton("Bishop");add(bishop);
        setTitle("choose a chess");
        setSize(500,80);
        setLocationRelativeTo(null);
        add(queen);
        add(rook);
        add(knight);
        add(bishop);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        queen.addActionListener(e ->{
            // TODO: 2022/5/23
            for (PawnChessComponent p : Pawns) {
                this.dispose();
                if (p.getChessColor().equals(ChessColor.WHITE) && p.getChessboardPoint().getX() == 0) {
                    p.balalaPawn(3);
                    return;
                }
                if (p.getChessColor().equals(ChessColor.BLACK) && p.getChessboardPoint().getX() == 7) {
                    p.balalaPawn(3);
                    return;
                }

            }
        });
        rook.addActionListener(e ->{
            this.dispose();
            for (PawnChessComponent p : Pawns) {
                if (p.getChessColor().equals(ChessColor.WHITE) && p.getChessboardPoint().getX() == 0) {
                    p.balalaPawn(0);
                    return;
                }
                if (p.getChessColor().equals(ChessColor.BLACK) && p.getChessboardPoint().getX() == 7) {
                    p.balalaPawn(0);
                    return;
                }
            }

        });
        knight.addActionListener(e ->{
            this.dispose();
            for (PawnChessComponent p : Pawns) {
                if (p.getChessColor().equals(ChessColor.WHITE) && p.getChessboardPoint().getX() == 0) {
                    p.balalaPawn(1);
                    return;
                }
                if (p.getChessColor().equals(ChessColor.BLACK) && p.getChessboardPoint().getX() == 7) {
                    p.balalaPawn(1);
                    return;
                }
            }

        });
        bishop.addActionListener(e ->{
            this.dispose();
            for (PawnChessComponent p : Pawns) {
                if (p.getChessColor().equals(ChessColor.WHITE) && p.getChessboardPoint().getX() == 0) {
                    p.balalaPawn(2);
                    return;
                }
                if (p.getChessColor().equals(ChessColor.BLACK) && p.getChessboardPoint().getX() == 7) {
                    p.balalaPawn(2);
                    return;
                }
            }

        });
    }
}