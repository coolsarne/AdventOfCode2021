import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Arne Cools
 * 08/12/2021
 */
public class Main_8_1 {
    public static void main(String[] args) throws IOException, IOException {
        File inputFile = new File("resources/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(inputFile));

        //Parsing the dataset
        List<String> input = new LinkedList<>();
        int length = 0;
        String line;
        while ((line = br.readLine()) != null) {
            String firstParse = line.split(" \\| ")[1];
            input.addAll(List.of(firstParse.split(" ")));
        }

        int count = 0;
        for (String s : input) {
            int l = s.length();
            if (l == 2 || l == 4 || l == 3 || l == 7) count++;
        }
        System.out.println("input = " + input);
        System.out.println("count = " + count);
    }
}
