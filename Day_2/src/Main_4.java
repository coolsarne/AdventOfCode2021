import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Arne Cools
 * 02/12/2021
 */
public class Main_4 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("resources/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(inputFile));

        String line;
        int xpos = 0;
        int ypos = 0;
        int aim = 0;
        while ((line = br.readLine()) != null) {
            String command = line.split(" ")[0];
            int value = Integer.parseInt(line.split(" ")[1]);
            switch (command) {
                case "forward":
                    xpos += value;
                    ypos += aim * value;
                    break;
                case "up":
                    aim -= value;
                    break;
                case "down":
                    aim += value;
                    break;
                default:
                    break;
            }
        }
        long movement = (long) xpos * ypos;
        System.out.println("movement = " + movement);

    }
}
