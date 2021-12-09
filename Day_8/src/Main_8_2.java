import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Arne Cools
 * 08/12/2021
 */
public class Main_8_2 {
    public static void main(String[] args) throws IOException, IOException {
        File inputFile = new File("resources/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(inputFile));

        //Parsing the dataset
        List<String[]> firstInputPart = new LinkedList<>();
        List<String[]> secondInputPart  = new LinkedList<>();
        int length = 0;
        String line;
        while ((line = br.readLine()) != null) {
            String parseOne = line.split(" \\| ")[0];
            String parseTwo = line.split(" \\| ")[1];
            firstInputPart.add(parseOne.split(" "));
            secondInputPart.add(parseTwo.split(" "));
        }
        int count = 0;
        long total = 0;
        for (String[] stringArray : firstInputPart) {
            Map<Integer, String> valueMap = new HashMap<>();
            for (String s : stringArray) { //find 1 4 7 8
                int l = s.length();
                if (l == 2) valueMap.put(1, s);
                else if (l == 3) valueMap.put(7, s);
                else if (l == 4) valueMap.put(4, s);
                else if (l == 7) valueMap.put(8, s);
            }
            for (String s : stringArray) { //find 6
                if (s.length() == 6) {
                    String one = valueMap.get(1);
                    int commonCountSix = 0;
                    for (int i = 0; i < s.length(); i++) {
                        for (int j = 0; j < one.length(); j++) {
                            if (s.charAt(i) == one.charAt(j)) commonCountSix++;
                        }
                    }
                    if (commonCountSix == 1) valueMap.put(6, s);
                    else { //find 0
                        String four = valueMap.get(4);
                        int commonCountZero = 0;
                        for (int i = 0; i < s.length(); i++) {
                            for (int j = 0; j < four.length(); j++) {
                                if (s.charAt(i) == four.charAt(j)) commonCountZero++;
                            }
                        }
                        if (commonCountZero == 3) valueMap.put(0, s);
                        else valueMap.put(9, s); //find 9
                    }
                }
            }
            for (String s : stringArray) { //find 5
                if (s.length() == 5){
                    String six = valueMap.get(6);
                    int commonCountFive = 0;
                    for (int i = 0; i < s.length(); i++) {
                        for (int j = 0; j < six.length(); j++) {
                            if (s.charAt(i) == six.charAt(j)) commonCountFive++;
                        }
                    }
                    if (commonCountFive == 5) valueMap.put(5, s);
                    else { //find 3
                        String one = valueMap.get(1);
                        int commonCountThree = 0;
                        for (int i = 0; i < s.length(); i++) {
                            for (int j = 0; j < one.length(); j++) {
                                if (s.charAt(i) == one.charAt(j)) commonCountThree++;
                            }
                        }
                        if (commonCountThree == 2) valueMap.put(3, s);
                        else valueMap.put(2, s); //find 2
                    }
                }
            }
            StringBuilder number = new StringBuilder();
            for (String string : secondInputPart.get(count)) {
                char[] first = string.toCharArray();
                for (Map.Entry<Integer, String> integerStringEntry : valueMap.entrySet()) {
                    char[] second = integerStringEntry.getValue().toCharArray();
                    Arrays.sort(first);
                    Arrays.sort(second);
                    if (Arrays.equals(first, second)){
                        number.append(integerStringEntry.getKey());
                    }
                }
            }
            total += Long.parseLong(number.toString());
            count++;
        }
        System.out.println("total = " + total);

    }
}
