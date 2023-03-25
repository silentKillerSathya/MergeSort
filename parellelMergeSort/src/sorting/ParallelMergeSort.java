package sorting;
import java.util.Arrays;

public class ParallelMergeSort {

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 3, 7, 1, 8, 6, 4};
        parallelMergeSort(arr, 4); 
        System.out.println(Arrays.toString(arr));
    }

    public static void parallelMergeSort(int[] arr, int numThreads) {
        if (numThreads < 2) {
            mergeSort(arr); 
        } 
        else if (arr.length > 1) {
            int[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
            int[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);

            Thread leftThread = new Thread(() -> parallelMergeSort(left, numThreads / 2));
            Thread rightThread = new Thread(() -> parallelMergeSort(right, numThreads / 2));
            leftThread.start();
            rightThread.start();

            try {
                leftThread.join();
                rightThread.join();
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            merge(arr, left, right);
        }
    }

    public static void mergeSort(int[] arr) {
    	
        if (arr.length > 1) {
        	
        	System.out.println(Arrays.toString(arr)+"  @@");
            int[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
            int[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);

            mergeSort(left);
            mergeSort(right);

            merge(arr, left, right);
        }
    }

    public static void merge(int[] arr, int[] left, int[] right) {
    	
    	System.out.println(Arrays.toString(right)+"  **");
    	System.out.println(Arrays.toString(left)+" **");
    	
        int i = 0;
        int j = 0;
        int k = 0;
        
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
}