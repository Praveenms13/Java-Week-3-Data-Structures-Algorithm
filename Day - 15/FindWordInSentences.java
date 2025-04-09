public class FindWordInSentences {
    public static String findSentence(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word)) return sentence;
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[] sentences = {
            "The quick brown fox jumps over the lazy dog.",
            "Java is a powerful programming language.",
            "Artificial intelligence is the future."
        };
        String word = "Java";
        String result = findSentence(sentences, word);
        System.out.println("Sentence containing the word: " + result);
    }
}
