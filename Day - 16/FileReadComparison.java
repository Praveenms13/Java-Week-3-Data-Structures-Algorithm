import java.io.*;

public class FileReadComparison {
    public static void readWithFileReader(String filePath) throws IOException {
        FileReader reader = new FileReader(filePath);
        while (reader.read() != -1) {
        }
        reader.close();
    }

    public static void readWithInputStreamReader(String filePath) throws IOException {
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath));
        while (reader.read() != -1) {
        }
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        String filePath = "largefile.txt"; // Ensure this file exists

        long start = System.nanoTime();
        readWithFileReader(filePath);
        System.out.println("FileReader Time: " + (System.nanoTime() - start) / 1e6 + "ms");

        start = System.nanoTime();
        readWithInputStreamReader(filePath);
        System.out.println("InputStreamReader Time: " + (System.nanoTime() - start) / 1e6 + "ms");
    }
}
