package save_write;

import controller.GameController;
import model.*;
import view.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 存储规范：每一行：存储第i个棋盘的全部棋子位置//0-63//该谁下//64//
 * <br>
 * todo：写文件
 * <br>
 * todo  :readd hashcode<br>
 * todo  :add user and password<br>
 * readFileByFileReader(String path)<br>
 * convertToChessboard(List<String> readlines)<br>
 * writeFileByFileWriter(String path, ArrayList<ChessComponent[][]> store)<br>
 */
public class Save_Write {
    ArrayList<ChessComponent[][]> store;
    ArrayList<ChessColor> currentColor = new ArrayList<>();
    int a=0;
    ///String hashCode;

    public List<String> readFileByFileReader(String path) {
        a=0;
        if(!path.substring(path.length()-4).equals(".txt")){
            GameController.setError(104);
            a=1;
            return null;
        }
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            List<String> readLines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                readLines.add(line);
            }
            reader.close();
            fileReader.close();
            return readLines;
        } catch (IOException e) {
        }
        return null;
    }

    public boolean convertToChessboard(List<String> readlines) {
        if (a==1){
            return false;
        }
        store = new ArrayList<>();
        currentColor = new ArrayList<>();
        if (readlines==null) {
            GameController.setError(101);
            return false;
        }
        for (int i = 0; i < readlines.size(); i++) {
            String pieces = readlines.get(i);
            System.out.println((pieces.charAt(pieces.length()-1)));
            if(pieces.charAt(pieces.length()-1)!='w'&&pieces.charAt(pieces.length()-1)!='b')
            {
                GameController.setError(103);
                return false;
            }
            if (pieces.length() != 65) {
                GameController.setError(101);
                return false;
            }
            ChessComponent[][] chessComponents = new ChessComponent[8][8];
            for (int j = 0; j < pieces.length() - 1; j++) {
                int a=j/8;
                int b=j%8;
                switch (pieces.charAt(j)) {
                    case 'r' -> {
                        chessComponents[a][b] = new RookChessComponent(new ChessboardPoint(j / 8, j % 8),ChessColor.WHITE);
                    }
                    case 'n' -> {
                        chessComponents[a][b] = new KnightChessComponent(new ChessboardPoint(j / 8, j % 8),ChessColor.WHITE);
                    }
                    case 'b' -> {
                        chessComponents[a][b] = new BishopChessComponent(new ChessboardPoint(j / 8, j % 8),ChessColor.WHITE);
                    }
                    case 'q' -> {
                        chessComponents[a][b] = new QueenChessComponent(new ChessboardPoint(j / 8, j % 8),ChessColor.WHITE);
                    }
                    case 'k' -> {
                        chessComponents[a][b] = new KingChessComponent(new ChessboardPoint(j / 8, j % 8),ChessColor.WHITE);
                    }
                    case 'p' -> {
                        chessComponents[a][b] = new PawnChessComponent(new ChessboardPoint(j / 8, j % 8),ChessColor.WHITE);
                    }
                    case 'R' -> {
                        chessComponents[a][b] = new RookChessComponent(new ChessboardPoint(j / 8, j % 8),ChessColor.BLACK);
                    }
                    case 'N' -> {
                        chessComponents[a][b] = new KnightChessComponent(new ChessboardPoint(j / 8, j % 8),ChessColor.BLACK);
                    }
                    case 'B' -> {
                        chessComponents[a][b] = new BishopChessComponent(new ChessboardPoint(j / 8, j % 8),ChessColor.BLACK);
                    }
                    case 'Q' -> {
                        chessComponents[a][b] = new QueenChessComponent(new ChessboardPoint(j / 8, j % 8),ChessColor.BLACK);
                    }
                    case 'K' -> {
                        chessComponents[a][b] = new KingChessComponent(new ChessboardPoint(j / 8, j % 8),ChessColor.BLACK);
                    }
                    case 'P' -> {
                        chessComponents[a][b] = new PawnChessComponent(new ChessboardPoint(j / 8, j % 8),ChessColor.BLACK);
                    }
                    case '_'->{
                        chessComponents[a][b] = new EmptySlotComponent(new ChessboardPoint(j / 8, j % 8),ChessColor.NONE);
                    }
                    default -> GameController.setError(102);
                }
            }store.add(chessComponents);
            switch (pieces.charAt(64)) {
                case 'w' -> currentColor.add(ChessColor.WHITE);
                case 'b' -> currentColor.add(ChessColor.BLACK);
            }
        }
        /// hashCode = readlines.get(readlines.size() - 1);
        ///if (hashCode == String.valueOf(String.join("", readlines).hashCode())) {
        return true;
        /// }
        /// return false;
    }

    public List<String> convertToList(ArrayList<ChessComponent[][]> store) {
        List<String> lines = new ArrayList<>();
        int lengths = store.size();
        for (int i = 0; i < lengths; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    sb.append(store.get(i)[j][k].toChar());
                }
            }
            sb.append(currentColor.get(i).toChar());
            lines.add(sb.toString());
            lines.add("\n");
        }
        ///  lines.add(String.valueOf(String.join("", lines).hashCode()));
        return lines;
    }

    public void writeFileByFileWriter(String path, ArrayList<ChessComponent[][]> store) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            List<String> lines = this.convertToList(store);
            for (String line : lines) {
                writer.write(line);
            }
            writer.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFileByFileWriter(String path, List<String> lines) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (String line : lines
            ) {
                writer.write(line);
            }
            writer.close();
            fileWriter.close();
        } catch (IOException e) {
        }
    }

    //getters and setters
    public ArrayList<ChessComponent[][]> getStore() {
        return store;
    }

    public void setStore(ArrayList<ChessComponent[][]> store) {
        this.store = store;
    }

    public ArrayList<ChessColor> getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(ArrayList<ChessColor> currentColor) {
        this.currentColor = currentColor;
    }
}