import view.SignInPlayer1Frame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SignInPlayer1Frame signInPlayer1Frame = new SignInPlayer1Frame(300, 200);
            signInPlayer1Frame.setVisible(true);
        });
    }
}