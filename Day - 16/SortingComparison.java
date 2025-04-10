import java.util.Arrays;

public class SortingComparison {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

    public static void mergeSort(int[] arr) {
        Arrays.sort(arr); // For demo purposes, using built-in MergeSort
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low-1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        int temp = arr[i+1]; arr[i+1] = arr[high]; arr[high] = temp;
        return i+1;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[10000];
        int[] arr2 = new int[10000];
        int[] arr3 = new int[10000];

        for (int i = 0; i < arr1.length; i++) {
            int val = (int)(Math.random() * 100000);
            arr1[i] = arr2[i] = arr3[i] = val;
        }

        long start = System.nanoTime();
        bubbleSort(arr1);
        System.out.println("Bubble Sort Time: " + (System.nanoTime() - start)/1e6 + "ms");

        start = System.nanoTime();
        mergeSort(arr2);
        System.out.println("Merge Sort Time: " + (System.nanoTime() - start)/1e6 + "ms");

        start = System.nanoTime();
        quickSort(arr3, 0, arr3.length - 1);
        System.out.println("Quick Sort Time: " + (System.nanoTime() - start)/1e6 + "ms");
    }
}
