import java.io.*;

public class InputToFile {
    public static void readAndWrite(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        String input;
        while (!(input = reader.readLine()).equalsIgnoreCase("exit")) {
            writer.write(input);
            writer.newLine();
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Enter the file name to write input to: ");
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = consoleReader.readLine();
        readAndWrite(fileName);
    }
}
