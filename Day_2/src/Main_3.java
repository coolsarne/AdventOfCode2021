import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Arne Cools
 * 02/12/2021
 */
public class Main_3 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("resources/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(inputFile));

        String line;
        int xpos = 0;
        int ypos = 0;
        while ((line = br.readLine()) != null) {
            String command = line.split(" ")[0];
            int value = Integer.parseInt(line.split(" ")[1]);
            switch (command) {
                case "forward":
                    xpos += value;
                    break;
                case "up":
                    ypos -= value;
                    break;
                case "down":
                    ypos += value;
                    break;
                default:
                    break;
            }
        }
        int movement = xpos * ypos;
        System.out.println("movement = " + movement);

    }
}
