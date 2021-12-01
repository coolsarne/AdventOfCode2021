import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Arne Cools
 * 01/12/2021
 */
public class Main_2 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("resources/input");
        BufferedReader br = new BufferedReader(new FileReader(inputFile));

        List<Integer> valuesList = new ArrayList<>();
        String line;
        while((line = br.readLine()) != null){
            valuesList.add(Integer.parseInt(line));
        }
        int[] valueArray = valuesList.stream().mapToInt(i -> i).toArray();
        List<Integer> sumList = new ArrayList<>();
        for (int i = 0; i < valueArray.length - 2; i++) {
            sumList.add(valueArray[i] + valueArray[i + 1] + valueArray[i + 2]);
        }
        int[] sumArray = sumList.stream().mapToInt(i -> i).toArray();
        int largerCount = 0;
        for (int i = 1 ; i < sumArray.length; i++) {
            if (sumArray[i] > sumArray[i - 1]) largerCount++;
        }
        System.out.println(largerCount);
    }
}
