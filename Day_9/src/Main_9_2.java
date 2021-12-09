import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Arne Cools
 * 09/12/2021
 */
public class Main_9_2 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("resources/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(inputFile));

        //Parsing the dataset
        List<int[]> input = new LinkedList<>();
        String line;
        int arrayXdim = 0;
        int length = 0;
        while ((line = br.readLine()) != null) {
            input.add(Arrays.stream(line.split("")).mapToInt(Integer::parseInt).toArray());
        }
        int[][] heights = input.toArray(new int[0][]);
        int[][] newHeights = new int[heights.length + 2][heights[0].length + 2];
        for (int i = 0; i < newHeights.length; i++) {
            for (int j = 0; j < newHeights[i].length; j++) {
                if (i == 0 || j == 0 || i == newHeights.length - 1 || j == newHeights[i].length - 1)
                    newHeights[i][j] = 11;
                else newHeights[i][j] = heights[i - 1][j - 1];
            }
        }
        List<Integer> lowPoints = new LinkedList<>();
        for (int i = 0; i < newHeights.length; i++) {
            for (int j = 0; j < newHeights[i].length; j++) {
                if (i > 0 && j > 0 && i < newHeights.length - 1 && j < newHeights[i].length - 1) {
                    if (newHeights[i][j] < newHeights[i - 1][j] && newHeights[i][j] < newHeights[i + 1][j] && newHeights[i][j] < newHeights[i][j - 1] && newHeights[i][j] < newHeights[i][j + 1])
                        lowPoints.add(newHeights[i][j]);
                }
            }
        }
        List<Integer> basinSizes = new LinkedList<>();
        for (int i = 0; i < newHeights.length; i++) {
            for (int j = 0; j < newHeights[i].length; j++) {
                for (Integer lowPoint : lowPoints) {
                    if (newHeights[i][j] == lowPoint) {

                    }
                }
            }
        }

    }
}
