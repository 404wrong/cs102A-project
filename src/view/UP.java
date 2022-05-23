package view;


import javax.swing.*;
import java.awt.*;

public class UP extends JFrame {
    private JButton queen,rook,knight,bishop;

    public UP(Chessboard chessboard, int x, int y){
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

        });
        rook.addActionListener(e ->{

        });
        knight.addActionListener(e ->{

        });
        bishop.addActionListener(e ->{

        });
    }
}