import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Arne Cools
 * 08/12/2021
 */
public class Main_6_1 {
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
        System.out.print("Amount of days to count: ");
        int days = sc.nextInt();

        for (int i = 1; i <= days; i++) {
            for (int j = 0; j < currentState.size(); j++) {
                if (currentState.get(j) == 0){
                    currentState.add(9);
                    currentState.set(j, 6);
                }
                else currentState.set(j, currentState.get(j) - 1);
            }
            System.out.println("Day " + i + " = " + currentState);

        }
        System.out.println("\nAmount of lanternfish after " + days + " days: " + currentState.size());
    }
}

