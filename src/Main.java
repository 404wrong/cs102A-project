import addModel.Play;
import view.SignInPlayer1Frame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String file = "resource/暗猫 - 海滨之梦-A-Dream-by-the-Sea.mp3";

        Play play = new Play(file);
        // 开启
        play.start();

        SwingUtilities.invokeLater(() -> {
            SignInPlayer1Frame signInPlayer1Frame = new SignInPlayer1Frame(300, 200);
            signInPlayer1Frame.setVisible(true);
        });
    }
}