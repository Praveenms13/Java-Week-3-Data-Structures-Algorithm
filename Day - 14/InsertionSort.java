public class InsertionSort {
    public static void main(String[] args) {
        int[] empIDs = { 104, 102, 109, 101, 105 };

        for (int i = 1; i < empIDs.length; i++) {
            int key = empIDs[i];
            int j = i - 1;
            while (j >= 0 && empIDs[j] > key) {
                empIDs[j + 1] = empIDs[j];
                j--;
            }
            empIDs[j + 1] = key;
        }

        System.out.println("Sorted Employee IDs:");
        for (int id : empIDs) {
            System.out.print(id + " ");
        }
    }
}
