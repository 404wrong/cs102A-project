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
            // TODO: 2022/5/23  
            this.dispose();
        });
        rook.addActionListener(e ->{
            // TODO: 2022/5/23  
            this.dispose();
        });
        knight.addActionListener(e ->{
            // TODO: 2022/5/23  
            this.dispose();
        });
        bishop.addActionListener(e ->{
            // TODO: 2022/5/23  
            this.dispose();
        });
    }
}