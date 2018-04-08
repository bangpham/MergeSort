public class MergeSort() {


    /**
     * Implementation of merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    @SuppressWarnings("unchecked")
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("array or comparator is null");
        }
        int lowIndex = 0;
        int highIndex = arr.length - 1;
        T[] temp = (T[]) new Object[arr.length];
        mergeSort(arr, comparator, lowIndex, highIndex, temp);
    }

    /**
     * Private recursive helper method of merge sort.
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @param low the left most index of the array
     * @param high the right most index of the array
     * @param temp a second helper array.
     */
    private static <T> void mergeSort(T[] arr, Comparator<T> comparator,
            int low, int high, T[] temp) {
        if (low == high) {
            return;
        }
        int mid = 0;
        if (low < high) {
            mid = (1 + low + high) / 2;
            mergeSort(arr, comparator, low, mid - 1, temp);
            mergeSort(arr, comparator, mid, high, temp);
            merge(arr, comparator, low, mid, high, temp);
        }
    }
    
    /**
     * Private helper method to merge the sub-arrays.
     * @param arr the array to be sorted.
     * @param comparator the Comparator used to compare the data in arr.
     * @param low the left most index of the array
     * @param mid the middle point index of the array
     * @param high the right most index of the array
     * @param temp a second helper array.
     * @param <T> data type to sort.
     */
    private static <T> void merge(T[] arr, Comparator<T> comparator,
            int low, int mid, int high, T[] temp) {
        for (int i = low; i <= high; i++) {
            temp[i] = arr[i];
        }
        int tempLength = high - low + 1;
        int midHigh = mid;
        int midLow = mid - 1;
        int tempIndex = low;
        int j = 0;
        while (low <= midLow && midHigh <= high) {
            if (comparator.compare(arr[low], arr[midHigh]) <= 0) {
                temp[tempIndex++] = arr[low++];
            } else {
                temp[tempIndex++] = arr[midHigh++];
            }
        }
        while (low <= midLow) {
            temp[tempIndex++] = arr[low++];
        }
        while (midHigh <= high) {
            temp[tempIndex++] = arr[midHigh++];
        }
        while (j < tempLength) {
            arr[high] = temp[high];
            high--;
            j++;
        }
    }
}
