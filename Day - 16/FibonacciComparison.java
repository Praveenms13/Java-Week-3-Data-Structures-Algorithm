public class FibonacciComparison {
    public static int fibonacciRecursive(int n) {
        if (n <= 1)
            return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciIterative(int n) {
        if (n <= 1)
            return n;
        int a = 0, b = 1, sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static void main(String[] args) {
        int n = 30;

        long start = System.nanoTime();
        System.out.println("Recursive: " + fibonacciRecursive(n));
        System.out.println("Recursive Time: " + (System.nanoTime() - start) / 1e6 + "ms");

        start = System.nanoTime();
        System.out.println("Iterative: " + fibonacciIterative(n));
        System.out.println("Iterative Time: " + (System.nanoTime() - start) / 1e6 + "ms");
    }
}
