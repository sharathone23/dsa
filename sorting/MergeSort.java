package sorting;

import java.util.Arrays;

public class MergeSort {

    /**
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     * @param input
     */
    public static void sort(int[] input){
        mergeSort(0,input.length-1,input);
    }

    private static void mergeSort(int left, int right, int[] input){
        if(left < right){
            int mid = left + (right-left)/2;
            mergeSort(left, mid, input);
            mergeSort(mid+1, right,input);
            merge(left,mid,right, input);
        }
    }

    private static void merge(int left, int mid, int right, int[] input){
        int m = mid - left + 1;
        int n = right - mid;
        int[] leftTemp = new int[m];
        int[] rightTemp = new int[n];
        for(int i=0;i<m;i++){
            leftTemp[i] = input[left+i];
        }
        for(int i=0;i<n;i++){
            rightTemp[i] = input[mid+1+i];
        }
        int i = 0;
        int j = 0;
        int index = left;
        while(i<m && j<n){
            if(leftTemp[i] <= rightTemp[j]){
                input[index++] = leftTemp[i++];
            }else{
                input[index++] = rightTemp[j++];
            }
        }
        while(i<m){
            input[index++] = leftTemp[i++];
        }
        while(j<n){
            input[index++] = rightTemp[j++];
        }
    }

    public static void main(String[] args){
        int[] input = new int[]{3,6,7,5,1,2,9,4,8};
        System.out.println("Before Sorting: " + Arrays.toString(input));
        MergeSort.sort(input);
        System.out.println("After Sorting: " + Arrays.toString(input));
    }
}
