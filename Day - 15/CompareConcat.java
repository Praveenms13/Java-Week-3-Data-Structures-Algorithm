public class CompareConcat {
    public static void compare() {
        long start, end;

        StringBuilder sb = new StringBuilder();
        start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            sb.append("hello");
        }
        end = System.nanoTime();
        System.out.println("StringBuilder: " + (end - start) + " ns");

        StringBuffer sbf = new StringBuffer();
        start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            sbf.append("hello");
        }
        end = System.nanoTime();
        System.out.println("StringBuffer: " + (end - start) + " ns");
    }

    public static void main(String[] args) {
        compare();
    }
}
