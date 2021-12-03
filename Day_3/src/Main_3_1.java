import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Arne Cools
 * 03/12/2021
 */
public class Main_3_1 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("resources/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(inputFile));

        int linecount = 0;
        List<Integer> oneBitCounts = new LinkedList<>();
        List<Integer> zeroBitCounts = new LinkedList<>();
        String line;
        while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                if (linecount == 0){
                    oneBitCounts.add(i, 0);
                    zeroBitCounts.add(i, 0);
                }
                if (line.charAt(i) == '1'){
                    oneBitCounts.set(i, oneBitCounts.get(i) + 1);
                }
            }
            linecount++;
        }
        for (int i = 0; i < oneBitCounts.size(); i++) {
            if ((linecount / 2) - oneBitCounts.get(i) < 0){
                oneBitCounts.set(i, 1);
                zeroBitCounts.set(i, 0);
            } else {
                oneBitCounts.set(i, 0);
                zeroBitCounts.set(i, 1);
            }
        }
        StringBuilder gammaRateString = new StringBuilder();
        StringBuilder epsilonRateString = new StringBuilder();
        for (int i = 0; i < oneBitCounts.size(); i++) {
            gammaRateString.append(oneBitCounts.get(i));
            epsilonRateString.append(zeroBitCounts.get(i));
        }
        int gammaRate = Integer.parseInt(gammaRateString.toString(),2);
        int epsilonRate = Integer.parseInt(epsilonRateString.toString(),2);

        long powerConsumption = (long) gammaRate * epsilonRate;
        System.out.println("powerConsumption = " + powerConsumption);

    }
}
