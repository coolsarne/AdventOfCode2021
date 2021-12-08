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
public class Main_6_2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        File inputFile = new File("resources/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(inputFile));

        //Parsing the dataset
        List<Integer> currentState = new LinkedList<>();
        int length = 0;
        String line;
        while ((line = br.readLine()) != null) {
            currentState = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        }
        int[] input = currentState.stream().mapToInt(i -> i).toArray();
        long[] generations = new long[9];
        Arrays.fill(generations, 0);

        for (int age : input) {
            generations[age]++;
        }
        System.out.print("Amount of days to count: ");
        int days = sc.nextInt();

        for (int i = 0; i < days; i++) {
            long parents = generations[0];
            for (int j = 1; j < generations.length; j++) {
                generations[j - 1] = generations[j];
            }
            generations[6] += parents;
            generations[8] = parents;
        }

        long total = Arrays.stream(generations).sum();
        System.out.println("\nAmount of lanternfish after " + days + " days: " + total);
    }
}
