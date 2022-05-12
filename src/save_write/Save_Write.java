package save_write;

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
    ///String hashCode;

    public List<String> readFileByFileReader(String path) {
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
            e.printStackTrace();
        }
        return null;
    }

    /**
     * todo:在将棋子类构建后引入for循环确定棋子类型
     */
    public boolean convertToChessboard(List<String> readlines) {
        store = new ArrayList<>();
        currentColor = new ArrayList<>();
        if (readlines.size() == 0) {
            return false;
        }
        for (int i = 0; i < readlines.size(); i++) {
            String pieces = readlines.get(i);
            if (pieces.length() != 65)
                return false;
            ChessComponent[][] chessComponents = new ChessComponent[8][8];
            for (int j = 0; j < pieces.length() - 1; j++) {
                switch (pieces.charAt(j)) {
                    case 'r' -> {
                        chessComponents[j / 8][j % 8] = new RookChessComponent(new ChessboardPoint(j / 8, j % 8));
                        chessComponents[j / 8][j % 8].setChessColor(ChessColor.WHITE);
                    }
                    case 'n' -> {
                        chessComponents[j / 8][j % 8] = new KnightChessComponent(new ChessboardPoint(j / 8, j % 8));
                        chessComponents[j / 8][j % 8].setChessColor(ChessColor.WHITE);
                    }
                    case 'b' -> {
                        chessComponents[j / 8][j % 8] = new BishopChessComponent(new ChessboardPoint(j / 8, j % 8));
                        chessComponents[j / 8][j % 8].setChessColor(ChessColor.WHITE);
                    }
                    case 'q' -> {
                        chessComponents[j / 8][j % 8] = new QueenChessComponent(new ChessboardPoint(j / 8, j % 8));
                        chessComponents[j / 8][j % 8].setChessColor(ChessColor.WHITE);
                    }
                    case 'k' -> {
                        chessComponents[j / 8][j % 8] = new KingChessComponent(new ChessboardPoint(j / 8, j % 8));
                        chessComponents[j / 8][j % 8].setChessColor(ChessColor.WHITE);
                    }
                    case 'p' -> {
                        chessComponents[j / 8][j % 8] = new PawnChessComponent(new ChessboardPoint(j / 8, j % 8));
                        chessComponents[j / 8][j % 8].setChessColor(ChessColor.WHITE);
                    }
                    case 'R' -> {
                        chessComponents[j / 8][j % 8] = new RookChessComponent(new ChessboardPoint(j / 8, j % 8));
                        chessComponents[j / 8][j % 8].setChessColor(ChessColor.BLACK);
                    }
                    case 'N' -> {
                        chessComponents[j / 8][j % 8] = new KnightChessComponent(new ChessboardPoint(j / 8, j % 8));
                        chessComponents[j / 8][j % 8].setChessColor(ChessColor.BLACK);
                    }
                    case 'B' -> {
                        chessComponents[j / 8][j % 8] = new BishopChessComponent(new ChessboardPoint(j / 8, j % 8));
                        chessComponents[j / 8][j % 8].setChessColor(ChessColor.BLACK);
                    }
                    case 'Q' -> {
                        chessComponents[j / 8][j % 8] = new QueenChessComponent(new ChessboardPoint(j / 8, j % 8));
                        chessComponents[j / 8][j % 8].setChessColor(ChessColor.BLACK);
                    }
                    case 'K' -> {
                        chessComponents[j / 8][j % 8] = new KingChessComponent(new ChessboardPoint(j / 8, j % 8));
                        chessComponents[j / 8][j % 8].setChessColor(ChessColor.BLACK);
                    }
                    case 'P' -> {
                        chessComponents[j / 8][j % 8] = new PawnChessComponent(new ChessboardPoint(j / 8, j % 8));
                        chessComponents[j / 8][j % 8].setChessColor(ChessColor.BLACK);
                    }
                    case '_'->{
                        chessComponents[j / 8][j % 8] = new EmptySlotComponent(new ChessboardPoint(j / 8, j % 8));
                        chessComponents[j / 8][j % 8].setChessColor(ChessColor.NONE);
                    }
                }
                store.add(chessComponents);
            }
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
        currentColor = new ArrayList<>();
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
        }
        ///  lines.add(String.valueOf(String.join("", lines).hashCode()));
        return lines;
    }

    public void writeFileByFileWriter(String path, ArrayList<ChessComponent[][]> store) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            List<String> lines = this.convertToList(store);
            for (String line : lines
            ) {
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
            e.printStackTrace();
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