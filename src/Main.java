import view.ChessGameFrame;
import view.SignInFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SignInFrame signInFrame = new SignInFrame(300, 200);
            signInFrame.setVisible(true);
        });
    }
}