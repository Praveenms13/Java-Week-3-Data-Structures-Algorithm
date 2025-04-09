import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReadWithEncoding {
    public static void readEncoded(String fileName, String charset) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), charset));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();
        System.out.print("Enter the charset (e.g., UTF-8): ");
        String charset = scanner.nextLine();
        try {
            readEncoded(fileName, charset);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        scanner.close();
    }
}
