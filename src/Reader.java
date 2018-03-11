
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.File;

public class Reader {


    public static List<String[]> readInputFile(String filepath) throws IOException {

        List<String[]> contents = new ArrayList<>();
        try {

            //Creating the scanner to read data from the text file
            Scanner scanner = new Scanner(new File(filepath));

            //Delimits data from the scanner with empty lines and a full stop to ensure that whole sentences are read
            scanner.useDelimiter("\r");

            //While the scanner still has data stored
            while (scanner.hasNext()) {
                String line = scanner.next();
                    //Add a sanitised sentence to the arraylist
                    contents.add(line.split(","));

            }
            scanner.close();

        } catch (IOException e) { //Catches the case where the input file cannot be accessed
            System.out.println("Input file not found, check file name, location, and permissions");

        }
        return contents;
    }

}
