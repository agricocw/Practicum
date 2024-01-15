import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PersonReader {

    public static void main(String[] args) {
        JFileChooser jfc = new JFileChooser();
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();

            try {
                displayPersonFile(selectedFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void displayPersonFile(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            // Assuming the first line contains column headers
            String headers = scanner.nextLine();
            System.out.println("ID#  Firstname    Lastname      Title      YOB");
            System.out.println("=====================================");

            int id = 1; // Initialize the id variable

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Use a regular expression to split by variable spaces
                String[] parts = line.split("\\s+");

                // Check if the array has enough elements before accessing
                if (parts.length >= 5) {
                    // Assuming your Person class has id, firstName, lastName, title, and yearOfBirth fields
                    String firstName = parts[1];
                    String lastName = parts[2];
                    String title = parts[3];
                    String yearOfBirth = parts[4];

                    // Manually format the output with spaces between the id and other columns
                    System.out.printf("%04d %-14s%-15s%-10s%-4s%n", id, firstName, lastName, title, yearOfBirth);

                    id++; // Increment the id for the next iteration
                } else {
                    // Handle the case where the line doesn't have enough elements
                    System.out.println("Invalid data format: " + line);
                }
            }
        }
    }
}