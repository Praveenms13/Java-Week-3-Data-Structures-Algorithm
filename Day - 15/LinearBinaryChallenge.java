import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class LinearBinaryChallenge {
    public static int findMissingPositive(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) set.add(num);
        for (int i = 1; i <= arr.length + 1; i++) {
            if (!set.contains(i)) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of elements in the array: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int missingPositive = findMissingPositive(arr);
        System.out.println("The first missing positive integer is: " + missingPositive);
        System.out.print("\nEnter the target value to search for: ");
        int target = scanner.nextInt();
        int targetIndex = binarySearch(arr, target);
        if (targetIndex != -1) {
            System.out.println("Target found at index: " + targetIndex);
        } else {
            System.out.println("Target not found.");
        }
        scanner.close();
    }
}
