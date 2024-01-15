import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> personDataList = new ArrayList<>();

        do {
            String personData = getPersonData(scanner);
            personDataList.add(personData);
        } while (SafeInput.getYNConfirm(scanner, "Do you want to enter another person?"));

        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter the file name to save the data");

        saveDataToFile(personDataList, fileName);

        System.out.println("Data saved successfully!");
    }

    public static String getPersonData(Scanner scanner) {
        String id = SafeInput.getRegExString(scanner, "Enter ID (6 digits)", "\\d{6}");
        String firstName = SafeInput.getNonZeroLenString(scanner, "Enter First Name");
        String lastName = SafeInput.getNonZeroLenString(scanner, "Enter Last Name");
        String title = SafeInput.getNonZeroLenString(scanner, "Enter Title");
        int yearOfBirth = SafeInput.getRangedInt(scanner, "Enter Year of Birth", 1000, 9999);

        return String.format("%s, %s, %s, %s, %d", id, firstName, lastName, title, yearOfBirth);
    }

    public static void saveDataToFile(ArrayList<String> data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}