public class CountingSort {
    public static void countingSort(int[] arr) {
        int max = 18, min = 10;
        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[arr.length];

        for (int age : arr) {
            count[age - min]++;
        }

        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public static void main(String[] args) {
        int[] ages = { 15, 11, 17, 10, 14, 13, 18, 12, 11 };
        countingSort(ages);

        System.out.println("Sorted Student Ages:");
        for (int age : ages) {
            System.out.print(age + " ");
        }
    }
}
