import java.util.Scanner;

public class PeakElement {
    public static int findPeak(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < arr[mid + 1]) 
                left = mid + 1;
            else 
                right = mid;
        }
        return arr[left];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements (space-separated):");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int peak = findPeak(arr);
        System.out.println("A peak element in the array is: " + peak);
        scanner.close();
    }
}
