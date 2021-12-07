import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Arne Cools
 * 04/12/2021
 */
public class Main_4_1 {
    static int[][] winningBoard = new int[5][5];
    static List<Integer> wonLine = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        File inputFile = new File("resources/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(inputFile));

        String line;
        int linecount = 0;
        int boardcount = 0;
        int boardLinecount = 0;
        int[][] boardBuilder = new int[5][5];
        List<Integer> drawNumbers = new LinkedList<>();
        List<int[][]> boards = new LinkedList<>();

        while ((line = br.readLine()) != null) {
            if (line.equals("") && linecount > 1) {
                boardcount++;
                boards.add(boardBuilder);
                boardLinecount = 0;
                boardBuilder = new int[5][5];
            } else if (linecount == 0) {
                drawNumbers = Arrays.stream(Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray()).boxed().collect(Collectors.toList());
            } else if (linecount > 1) {
                if (line.startsWith(" ")) line = line.replaceFirst(" ", "");
                int[] lineNumbers = Arrays.stream(line.split("[^0-9]+")).mapToInt(Integer::parseInt).toArray();
                System.arraycopy(lineNumbers, 0, boardBuilder[boardLinecount], 0, lineNumbers.length);
                boardLinecount++;
            }
            linecount++;
        }
        boardcount++;
        boards.add(boardBuilder);

        List<Integer> drawnNumbers = new LinkedList<>();
        for (Integer drawNumber : drawNumbers) {
            drawnNumbers.add(drawNumber);
            for (int[][] board : boards) {
                boolean won = checkIfWon(drawnNumbers, board);
                if (won){
                    for (int i = 0; i < board.length; i++) {
                        for (int j = 0; j < board[i].length; j++) {
                            System.out.print(board[i][j] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println("drawnNumbers = " + drawnNumbers);
                    System.out.println();
                    System.out.println("wonLine = " + wonLine);
                    break;
                }
            }
            if (wonLine.size() > 0) break;
        }
        int sumNotWonLineNumbers = 0;
        LinkedList<Integer> excludeThese = new LinkedList<>(drawnNumbers);
        for (int i = 0; i < winningBoard.length; i++) {
            for (int j = 0; j < winningBoard[i].length; j++) {
                sumNotWonLineNumbers += winningBoard[i][j];
                if (excludeThese.contains(winningBoard[i][j])){
                    excludeThese.remove(Integer.valueOf(winningBoard[i][j]));
                }
            }
        }
        for (Integer integer : drawnNumbers) {
            sumNotWonLineNumbers -= integer;
        }
        for (Integer integer : excludeThese) {
            sumNotWonLineNumbers += integer;
        }


        System.out.println("sumNotWonLineNumbers = " + sumNotWonLineNumbers);
        System.out.println("last Drawn number = " + drawnNumbers.get(drawnNumbers.size() -1));
        System.out.println("solution = " + sumNotWonLineNumbers * drawnNumbers.get(drawnNumbers.size() -1));

    }

    public static boolean checkIfWon(List<Integer> drawnNumbers, int[][] board) {
        boolean bingo = false;
        for (int i = 0; i < board.length; i++) {
            if (checkLine(drawnNumbers, board[i])){ //checks horizontally
                bingo = true;
                winningBoard = board;
                break;
            } else{
                List<Integer> verticalLine = new LinkedList<>();
                for (int j = 0; j < board[i].length; j++) {
                    verticalLine.add(board[j][i]);
                }
                if (checkLine(drawnNumbers, verticalLine.stream().mapToInt(k->k).toArray())){
                    bingo = true;
                    winningBoard = board;
                    break;
                }
            }
        }
        return bingo;
    }

    public static boolean checkLine(List<Integer> drawnNumbers, int[] line) {
        int foundNumbersCount = 0;
        for (int i = 0; i < line.length; i++) {
            for (Integer number : drawnNumbers) {
                if (line[i] == number){
                    foundNumbersCount++;
                    break;
                }
            }
            if (foundNumbersCount >= line.length) break;
        }
        if (foundNumbersCount >= line.length) Arrays.stream(line).forEach(wonLine::add);
        if (foundNumbersCount >= line.length) System.out.println("FOUND LINE!");
        return foundNumbersCount >= line.length;
    }

}
