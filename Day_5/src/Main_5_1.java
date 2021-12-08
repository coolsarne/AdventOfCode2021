import java.io.*;
import java.util.*;

/**
 * Arne Cools
 * 08/12/2021
 */
public class Main_5_1 {
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
        for (Integer integer : fromX) {
            if (!integer.equals(toX.get(fromX.indexOf(integer))) && fromY.get(fromX.indexOf(integer)).equals(toY.get(fromX.indexOf(integer)))) { //draw horizontal lines
                int steps = integer < toX.get(fromX.indexOf(integer)) ? 1 : -1;
                int xposToIncrement = integer;
                while (xposToIncrement != (toX.get(fromX.indexOf(integer)) + steps)) {
                    diagram[fromY.get(fromX.indexOf(integer))][xposToIncrement]++;
                    xposToIncrement += steps;
                }
            } else if (!fromY.get(fromX.indexOf(integer)).equals(toY.get(fromX.indexOf(integer))) && integer.equals(toX.get(fromX.indexOf(integer)))) { //draw vertical lines
                int steps = fromY.get(fromX.indexOf(integer)) < toY.get(fromX.indexOf(integer)) ? 1 : -1;
                int yposToIncrement = fromY.get(fromX.indexOf(integer));
                while (yposToIncrement != (toY.get(fromX.indexOf(integer)) + steps)) {
                    diagram[yposToIncrement][toX.get(fromX.indexOf(integer))]++;
                    yposToIncrement += steps;
                }
            }
            fromX.set(fromX.indexOf(integer), null); //makes sure that indexOf method will never encounter duplicate integer values
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
