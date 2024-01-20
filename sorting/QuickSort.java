package sorting;

import java.util.Arrays;

public class QuickSort {

    /**
     * Time Complexity: O(n log n) - Avg case
     * Time Complexity: O(n^2) - Worst case
     * Space Complexity: O(n)
     * @param input
     */
    public static void sortWithNaive(int[] input){
        quickSortWithNaivePartition(input,0,input.length-1);
    }

    /**
     * Time Complexity: O(n log n) - Avg case
     * Time Complexity: O(n^2) - Worst case
     * Space Complexity: O(log n) on average due to recursive function calls
     * @param input
     */
    public static void sortWithLomuto(int[] input){
        quickSortWithLomutoPartition(input,0,input.length-1);
    }

    private static void quickSortWithNaivePartition(int[] input, int left,int right){
        if(left < right){
            int p = naivePartition(input, left, right);
            quickSortWithNaivePartition(input,left,p-1);
            quickSortWithNaivePartition(input,p+1,right);
        }
    }

    private static void quickSortWithLomutoPartition(int[] input, int left,int right){
        if(left < right){
            int p = lomutoPartition(input, left, right);
            quickSortWithLomutoPartition(input,left,p-1);
            quickSortWithLomutoPartition(input,p+1,right);
        }
    }

    private static int lomutoPartition(int[] input, int left, int right){
        int pivot = input[right];
        int i = left; // place for swapping

        for (int j = left; j < right; j++) {
            if (input[j] <= pivot) {
                swap(i, j, input);
                i++;
            }
        }

        swap(i, right, input);
        return i;
    }

    private static int naivePartition(int[] input, int left, int right){
        int pivot = right;
        int[] temp = new int[right-left+1];
        int index = 0;
        //Store all the elements less than pivot in temp arr
        for(int i=left;i<left+temp.length;i++){
            if(input[i] < input[right]){
                temp[index++] = input[i];
            }
        }
        pivot = index;
        //Store the pivot element itself
        temp[index++] = input[right];
        //Store all the elements greater than pivot in temp arr
        for(int i=left;i<left+temp.length;i++){
            if(input[i] > input[right]){
                temp[index++] = input[i];
            }
        }
        //Update the elements in input with temp arr
        index = 0;
        for(int i=left;i<left+temp.length;i++){
            input[i] = temp[index++];
        }
        return pivot; // return pivot
    }

    private static void swap(int i, int j, int[] input){
        int temp = input[j];
        input[j] = input[i];
        input[i] = temp;
    }

    public static void main(String[] args){
        int[] input = new int[]{3,6,7,5,1,2,9,4,8};
        System.out.println("Before Sorting using Naive: " + Arrays.toString(input));
        QuickSort.sortWithNaive(input);
        System.out.println("After Sorting using Naive: " + Arrays.toString(input));
        input = new int[]{3,6,7,5,1,2,9,4,8};
        System.out.println("Before Sorting using Lomuto: " + Arrays.toString(input));
        QuickSort.sortWithLomuto(input);
        System.out.println("After Sorting using Lomuto: " + Arrays.toString(input));
    }
}
