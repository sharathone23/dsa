package sorting;

import java.util.Arrays;

public class BubbleSort {

    /**
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * @param input
     */
    public static void sort(int[] input){
        int right = input.length-1; // to avoid iterating already sorted elements
        for(int i=0;i<input.length-1;i++){
            for(int j=0;j<right;j++){
                if(input[j] > input[j+1]){
                    swap(j, j+1, input);
                }
            }
            right--;
        }
    }

    private static void swap(int i, int j, int[] input){
        int temp = input[j];
        input[j] = input[i];
        input[i] = temp;
    }

    public static void main(String[] args){
        int[] input = new int[]{3,6,7,5,1,2,9,4,8};
        System.out.println("Before Sorting: " + Arrays.toString(input));
        BubbleSort.sort(input);
        System.out.println("After Sorting: " + Arrays.toString(input));
    }
}
