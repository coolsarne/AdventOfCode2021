import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Arne Cools
 * 08/12/2021
 */
public class Main_7_1 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("resources/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(inputFile));

        //Parsing the dataset
        List<Integer> input = new LinkedList<>();
        int length = 0;
        String line;
        while ((line = br.readLine()) != null) {
            input = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        }

        int[] positions = input.stream().mapToInt(i -> i).toArray();
        int maxValue = Arrays.stream(positions).max().getAsInt();

        int[] fuelValues = new int[maxValue];
        for (int i = 0; i < maxValue; i++) {
            fuelValues[i] = countFuel(positions, i);
        }
        int lowest = Arrays.stream(fuelValues).min().getAsInt();
        System.out.println("lowest = " + lowest);
    }

    static int countFuel(int[] positions, int destination){
        int fuelCount = 0;
        for (int position : positions) {
            fuelCount += Math.abs(position - destination);
        }
        return fuelCount;
    }
}
