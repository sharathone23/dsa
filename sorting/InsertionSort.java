package sorting;

import java.util.Arrays;

public class InsertionSort {

    /**
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * @param input
     */
    public static void sort(int[] input){
        for(int i=1;i<input.length;i++){
            int key = input[i];
            int j=i-1;
            while(j>=0 && input[j]>key){
                input[j+1]=input[j];
                j--;
            }
            input[j+1]=key;
        }
    }

    public static void main(String[] args){
        int[] input = new int[]{3,6,7,5,1,2,9,4,8};
        System.out.println("Before Sorting: " + Arrays.toString(input));
        InsertionSort.sort(input);
        System.out.println("After Sorting: " + Arrays.toString(input));
    }
}
