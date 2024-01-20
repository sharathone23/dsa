package sorting;

import nonlinear.BinaryHeap;

import java.util.Arrays;

public class HeapSort {
    /**
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     * @param input
     */
    public static void sort(int[] input){
        BinaryHeap heap = new BinaryHeap(input.length); // Using custom implementation of BinaryHeap
        //build min heap by inserting all the elements of input one by one
        for(int i: input){
            heap.insert(i);
        }
        // min heap always has the minimum on top/root and maintains that property for each extract/removal
        for(int i=0;i<input.length;i++){
            input[i] = heap.extractMinOrMax();
        }
    }

    public static void main(String[] args){
        int[] input = new int[]{3,6,7,5,1,2,9,4,8};
        System.out.println("Before Sorting: " + Arrays.toString(input));
        HeapSort.sort(input);
        System.out.println("After Sorting: " + Arrays.toString(input));
    }
}
