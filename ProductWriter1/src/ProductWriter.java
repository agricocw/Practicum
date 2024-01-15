import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> productDataList = new ArrayList<>();

        
        do {
            String productData = getProductData(scanner);
            productDataList.add(productData);
        } while (SafeInput.getYNConfirm(scanner, "Do you want to enter another product?"));

        
        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter the file name to save the data");

        
        saveDataToFile(productDataList, fileName);

        System.out.println("Data saved successfully!");
    }

    
    public static String getProductData(Scanner scanner) {
        String id = SafeInput.getNonZeroLenString(scanner, "Enter ID");
        String name = SafeInput.getNonZeroLenString(scanner, "Enter Name");
        String description = SafeInput.getNonZeroLenString(scanner, "Enter Description");
        double cost = SafeInput.getDouble(scanner, "Enter Cost");

        return String.format("%s, %s, %s, %.2f", id, name, description, cost);
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