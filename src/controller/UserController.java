package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <br>存储模式：每个用户占据五行空间
 * <br>第一行为用户名
 * <br>第二行为密码哈希值
 * <br>第三行为用户胜场
 * <br>第四行为用户负场
 * <br>第五行为用户平场
 */
public class UserController {
    private List<String> Users=new ArrayList<>();
    private HashMap<String,String> users=new HashMap<>();
    private HashMap<String,Integer> win=new HashMap<>();
    private HashMap<String,Integer> lose=new HashMap<>();
    private HashMap<String,Integer> draw=new HashMap<>();
    private List<String> readLines = new ArrayList<>();

    public UserController(String path){
        getUserInformation(path);
    }
    /**
     *<br>0:无法找到该用户
     *<br>1：登陆成功
     *<br>-1：密码错误
     */
    public int hasThisUser(String user,String password){
        if (users.isEmpty()){
            return 0;
        }
        if(!users.containsKey(user)){
            return 0;
        }
        if(users.get(user).equals(password)){
            return 1;
        }
        return -1;
    }

    /**
     * <br>true:添加成功
     * <br>false：已存在此用户
     */
    public boolean addThisUser(String user,String password,String path){
        if(users.containsKey(user)){
            return false;
        }
        Users.add(user);
        users.put(user,password);
        win.put(user,0);
        lose.put(user,0);
        draw.put(user,0);
        writeFileByFileWriter(path);
        return true;
    }

    /**
     * 在getUserInformation(String path)中调用，无需直接调用
     */
    public boolean readFileByFileReader(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                readLines.add(line);
            }
            reader.close();
            fileReader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * todo：未判断是否为数字
     */
    public void getUserInformation(String path){
        if (!readFileByFileReader(path)){//如果不存在、读取错误则创造
            try {
                FileWriter fileWriter = new FileWriter(path);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                    writer.write("admin\n1450575459\n0\n0\n0\n");
                writer.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (readLines.size()==0){//如果不存在、读取错误则创造
            try {
                FileWriter fileWriter = new FileWriter(path);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write("admin\n1450575459\n0\n0\n0\n");
                writer.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (readLines.size()-readLines.size()/5*5!=0){//如果不存在、读取错误则创造
            try {
                FileWriter fileWriter = new FileWriter(path);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write("");
                writer.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < readLines.size()/5; i++) {
            Users.add(readLines.get(5*i));
            users.put(readLines.get(5*i),readLines.get(5*i+1));
            win.put(readLines.get(5*i),Integer.parseInt(readLines.get(5*i+2)));
            lose.put(readLines.get(5*i),Integer.parseInt(readLines.get(5*i+3)));
            draw.put(readLines.get(5*i),Integer.parseInt(readLines.get(5*i+4)));
        }
    }

    /**
     * 已调用renewUserInformation()
     */
    public void writeFileByFileWriter(String path) {
        renewUserInformation();
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (String line : readLines
            ) {
                writer.write(line+"\n");
            }
            writer.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renewUserInformation(){
        readLines=new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            readLines.add(Users.get(i));
            readLines.add(users.get(Users.get(i)));
            readLines.add(String.valueOf(win.get(Users.get(i))));
            readLines.add(String.valueOf(lose.get(Users.get(i))));
            readLines.add(String.valueOf(draw.get(Users.get(i))));
        }
    }
}
