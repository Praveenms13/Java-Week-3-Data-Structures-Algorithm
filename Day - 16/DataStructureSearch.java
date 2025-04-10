import java.util.*;

public class DataStructureSearch {
    public static void main(String[] args) {
        int N = 1000000;
        int target = N - 1;

        int[] array = new int[N];
        HashSet<Integer> hashSet = new HashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            array[i] = i;
            hashSet.add(i);
            treeSet.add(i);
        }

        long start = System.nanoTime();
        boolean found = false;
        for (int val : array) {
            if (val == target) {
                found = true;
                break;
            }
        }
        System.out.println("Array Search Time: " + (System.nanoTime() - start)/1e6 + "ms");

        start = System.nanoTime();
        hashSet.contains(target);
        System.out.println("HashSet Search Time: " + (System.nanoTime() - start)/1e6 + "ms");

        start = System.nanoTime();
        treeSet.contains(target);
        System.out.println("TreeSet Search Time: " + (System.nanoTime() - start)/1e6 + "ms");
    }
}
