public class ConcatenateStrings {
    public static String concatenate(String[] arr) {
        StringBuffer sb = new StringBuffer();
        for (String s : arr) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] arr = {"Hello", " ", "World", "!", " How", " are", " you?"};
        String result = concatenate(arr);
        System.out.println("Concatenated string: " + result);
    }
}
