import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Arne Cools
 * 03/12/2021
 */
public class Main_3_2 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("resources/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        List<String> originalValues = new LinkedList<>();
        String line;
        while ((line = br.readLine()) != null) {
            originalValues.add(line);
        }
        List<String> ogrValues = new LinkedList<>(originalValues);
        List<String> cosValues = new LinkedList<>(originalValues);
        for (int i = 0; i < originalValues.get(0).length(); i++) {
            char ogrBitcriteria = '0';
            char cosBitcriteria = '0';
            int onesCount = 0;
            int zerosCount = 0;
            for (String ogrValue : ogrValues) {
                if (ogrValue.charAt(i) == '1') onesCount++;
            }
            for (String cosValue : cosValues) {
                if (cosValue.charAt(i) == '0') zerosCount++;
            }
            if (onesCount >= ogrValues.size() - onesCount) ogrBitcriteria = '1';
            if (zerosCount > cosValues.size() - zerosCount) cosBitcriteria = '1';
            if (ogrValues.size() > 1) {
                for (Iterator<String> iterator = ogrValues.iterator(); iterator.hasNext(); ) {
                    if (iterator.next().charAt(i) != ogrBitcriteria) iterator.remove();
                }
            }
            if (cosValues.size() > 1) {
                for (Iterator<String> iterator = cosValues.iterator(); iterator.hasNext(); ) {
                    if (iterator.next().charAt(i) != cosBitcriteria) iterator.remove();
                }
            }
        }
        int ogrRate = Integer.parseInt(ogrValues.get(0),2);
        int cosRate = Integer.parseInt(cosValues.get(0),2);
        long lsRating = (long)ogrRate * cosRate;
        System.out.println("Life support rating = " + lsRating);
    }
}
