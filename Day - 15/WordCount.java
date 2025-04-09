import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordCount {
    public static int countWord(String fileName, String word) throws IOException {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.replaceAll("[^a-zA-Z0-9]", " ").split("\\s+");
                for (String w : words) {
                    if (w.equalsIgnoreCase(word)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        try {
            String fileName = "sample.txt";
            String wordToSearch = "hello";
            int result = countWord(fileName, wordToSearch);
            System.out.println("The word '" + wordToSearch + "' appears " + result + " times.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
