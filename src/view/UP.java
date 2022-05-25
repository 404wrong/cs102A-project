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

                if (p.getChessColor().equals(ChessColor.WHITE) && p.getChessboardPoint().getX() == 0) {
                    p.balalaPawn(3);
                    this.dispose();
                    return;
                }
                if (p.getChessColor().equals(ChessColor.BLACK) && p.getChessboardPoint().getX() == 7) {
                    p.balalaPawn(3);
                    this.dispose();
                    return;
                }

            }
        });
        rook.addActionListener(e ->{
            // TODO: 2022/5/23

            for (PawnChessComponent p : Pawns) {
                if (p.getChessColor().equals(ChessColor.WHITE) && p.getChessboardPoint().getX() == 0) {
                    p.balalaPawn(0);
                    this.dispose();
                    return;
                }
                if (p.getChessColor().equals(ChessColor.BLACK) && p.getChessboardPoint().getX() == 7) {
                    p.balalaPawn(0);
                    this.dispose();
                    return;
                }
            }

        });
        knight.addActionListener(e ->{
            // TODO: 2022/5/23

            for (PawnChessComponent p : Pawns) {
                if (p.getChessColor().equals(ChessColor.WHITE) && p.getChessboardPoint().getX() == 0) {
                    p.balalaPawn(1);
                    this.dispose();
                    return;
                }
                if (p.getChessColor().equals(ChessColor.BLACK) && p.getChessboardPoint().getX() == 7) {
                    p.balalaPawn(1);
                    this.dispose();
                    return;
                }
            }

        });
        bishop.addActionListener(e ->{
            // TODO: 2022/5/23

            for (PawnChessComponent p : Pawns) {
                if (p.getChessColor().equals(ChessColor.WHITE) && p.getChessboardPoint().getX() == 0) {
                    p.balalaPawn(2);
                    this.dispose();
                    return;
                }
                if (p.getChessColor().equals(ChessColor.BLACK) && p.getChessboardPoint().getX() == 7) {
                    p.balalaPawn(2);
                    this.dispose();
                    return;
                }
            }

        });
    }
}