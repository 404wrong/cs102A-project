package controller;

import java.util.Timer;
import java.util.TimerTask;

public class TimeController {
    public static long midTime;
    public static Timer timer = new Timer();

    public TimeController() {
        midTime = 30;
        timer.schedule(new TimerTask() {
            public void run() {
                long s = midTime;
                System.out.println(s + "s");
                if (midTime == 0) {
                    midTime=30;
                }
                midTime--;
            }
        }, 0, 1000);
    }
    public void resetTimeController(){
        midTime=30;
        timer.cancel();
        timer=new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                long s = midTime;
                System.out.println(s + "s");
                if (midTime == 0) {
                    midTime=30;
                }
                GameController.getChessGameFrame().timerLable.setText(String.valueOf(s));
                GameController.getChessGameFrame().timerLable.repaint();
                midTime--;
            }
        }, 0, 1000);
    }
}
