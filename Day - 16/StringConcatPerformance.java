public class StringConcatPerformance {
    public static void main(String[] args) {
        int N = 10000;

        long start = System.nanoTime();
        String s = "";
        for (int i = 0; i < N; i++) s += "a";
        System.out.println("String Time: " + (System.nanoTime() - start)/1e6 + "ms");

        start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append("a");
        System.out.println("StringBuilder Time: " + (System.nanoTime() - start)/1e6 + "ms");

        start = System.nanoTime();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < N; i++) sbf.append("a");
        System.out.println("StringBuffer Time: " + (System.nanoTime() - start)/1e6 + "ms");
    }
}
