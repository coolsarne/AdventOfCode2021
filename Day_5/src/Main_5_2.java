import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Arne Cools
 * 08/12/2021
 */
public class Main_5_2 {
    static int[][] diagram;

    public static void main(String[] args) throws IOException {
        File inputFile = new File("resources/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(inputFile));

        //Parsing the dataset
        int length = 0;
        List<Integer> fromX = new LinkedList<>();
        List<Integer> fromY = new LinkedList<>();
        List<Integer> toX = new LinkedList<>();
        List<Integer> toY = new LinkedList<>();
        String line;
        while ((line = br.readLine()) != null) {
            length++;
            fromX.add(Integer.parseInt(line.split(",")[0]));
            fromY.add(Integer.parseInt(line.split(",")[1].split(" -> ")[0]));
            toX.add(Integer.parseInt(line.split(",")[1].split(" -> ")[1]));
            toY.add(Integer.parseInt(line.split(",")[2]));
        }

        //generate diagram
        int maxX = 0;
        int maxY = 0;
        maxX = Collections.max(fromX) > Collections.max(toX) ? Collections.max(fromX) : Collections.max(toX);
        maxY = Collections.max(fromY) > Collections.max(toY) ? Collections.max(fromY) : Collections.max(toY);

        diagram = new int[++maxY][++maxX];

        List<Integer> tempFromX = new LinkedList<>(fromX);
        for (Integer integer : tempFromX) { //draw horizontal lines
            if (!integer.equals(toX.get(tempFromX.indexOf(integer))) && fromY.get(tempFromX.indexOf(integer)).equals(toY.get(tempFromX.indexOf(integer)))) {
                int steps = integer < toX.get(tempFromX.indexOf(integer)) ? 1 : -1;
                int xposToIncrement = integer;
                while (xposToIncrement != (toX.get(tempFromX.indexOf(integer)) + steps)) {
                    diagram[fromY.get(tempFromX.indexOf(integer))][xposToIncrement]++;
                    xposToIncrement += steps;
                }
            }
            tempFromX.set(tempFromX.indexOf(integer), -2); //makes sure that indexOf method will never encounter duplicate integer values
        }
        List<Integer> tempFromY = new LinkedList<>(fromY);
        for (Integer integer : tempFromY) { //draw vertical lines
            if (!integer.equals(toY.get(tempFromY.indexOf(integer))) && fromX.get(tempFromY.indexOf(integer)).equals(toX.get(tempFromY.indexOf(integer)))) {
                int steps = integer < toY.get(tempFromY.indexOf(integer)) ? 1 : -1;
                int yposToIncrement = integer;
                while (yposToIncrement != (toY.get(tempFromY.indexOf(integer)) + steps)){
                    diagram[yposToIncrement][toX.get(tempFromY.indexOf(integer))]++;
                    yposToIncrement += steps;
                }
            }
            tempFromY.set(tempFromY.indexOf(integer), -2); //makes sure that indexOf method will never encounter duplicate integer values
        }
        for (Integer integer : fromX) { //draw diagonal lines
            if (!integer.equals(toX.get(fromX.indexOf(integer))) && !fromY.get(fromX.indexOf(integer)).equals(toY.get(fromX.indexOf(integer)))){
                int xSteps = integer < toX.get(fromX.indexOf(integer)) ? 1 : -1;
                int ySteps = fromY.get(fromX.indexOf(integer)) < toY.get(fromX.indexOf(integer)) ? 1 : -1;
                int xPosToIncrement = integer;
                int yposToIncrement = fromY.get(fromX.indexOf(integer));
                while (xPosToIncrement != (toX.get(fromX.indexOf(integer)) + xSteps)){
                    diagram[yposToIncrement][xPosToIncrement]++;
                    xPosToIncrement += xSteps;
                    yposToIncrement += ySteps;
                }
            }
            fromX.set(fromX.indexOf(integer), -2); //makes sure that indexOf method will never encounter duplicate integer values
        }

        //check #overlapping lines
        int overlapCount = 0;
        for (int i = 0; i < diagram.length; i++) {
            for (int j = 0; j < diagram[i].length; j++) {
                if (diagram[i][j] >= 2) overlapCount++;
            }
        }
        System.out.println("overlapCount = " + overlapCount);

    }
}
