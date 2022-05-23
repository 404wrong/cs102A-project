package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private String users;

    private int win=0;

    private int lose=0;
    private int draw=0;

    public User (String users) {
        this.users = users;
    }

    public User(String users,int win,int lose,int draw){
        this.users=users;
        this.draw=draw;
        this.win=win;
        this.lose=lose;
    }
    public String getUsers() {
        return users;
    }

    public int getWin() {
        return win;
    }
    public void addWin() {
        win++;
    }
    public int getLose() {
        return lose;
    }
    public void addLose() {
        lose++;
    }
    public int getDraw() {
        return draw;
    }
    public void addDraw() {
        draw++;
    }
}
