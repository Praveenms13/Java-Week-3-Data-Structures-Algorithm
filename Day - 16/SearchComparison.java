import java.util.Arrays;

public class SearchComparison {
    public static boolean linearSearch(int[] arr, int target) {
        for (int num : arr) {
            if (num == target) return true;
        }
        return false;
    }

    public static boolean binarySearch(int[] arr, int target) {
        Arrays.sort(arr); // O(N log N)
        return Arrays.binarySearch(arr, target) >= 0;
    }

    public static void main(String[] args) {
        int[] dataset = new int[1000000];
        for (int i = 0; i < dataset.length; i++) dataset[i] = i;

        int target = 999999;

        long start = System.nanoTime();
        System.out.println("Linear: " + linearSearch(dataset, target));
        long end = System.nanoTime();
        System.out.println("Linear Search Time: " + (end - start) / 1e6 + "ms");

        start = System.nanoTime();
        System.out.println("Binary: " + binarySearch(dataset, target));
        end = System.nanoTime();
        System.out.println("Binary Search Time (incl. sort): " + (end - start) / 1e6 + "ms");
    }
}
