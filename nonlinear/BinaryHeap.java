package nonlinear;

import java.util.NoSuchElementException;

/**
 * A BinaryHeap class that implements a binary heap (either min-heap or max-heap).
 * The heap is implemented using an array and supports basic heap operations like
 * insert, extractMinOrMax, and deleteKey.
 *
 * @author Sharath Enugala
 * @version 1.0
 */
public class BinaryHeap {
    private final int[] heapArray;
    private int size;
    private final int capacity;

    private boolean isMinHeap;

    public BinaryHeap(int capacity){
        this.heapArray = new int[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.isMinHeap = true; // Default Min Heap
    }

    // Support to create Max Heap
    public BinaryHeap(int capacity, boolean isMinHeap){
        this.heapArray = new int[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.isMinHeap = isMinHeap;
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }
    private int getLeftIndex(int i) {
        return (2 * i + 1);
    }
    private int getRightIndex(int i) {
        return (2 * i + 2);
    }

    /**
     * Extracts the minimum or maximum key from the heap, depending on whether
     * it's a min-heap or max-heap.
     *
     * @return The extracted key.
     * @throws NoSuchElementException if the heap is empty.
     */
    public int extractMinOrMax(){
        if(isEmpty()) throw new NoSuchElementException("Heap is empty");
        int value = heapArray[0]; //Store the current minimum/maximum in a temp variable, used to return at the end.
        deleteKey(0); //delete the current minimum from heap.
        return value; // return the minimum/maximum value stored earlier when the heap was not modified
    }

    /**
     * Returns the current Minimum/Maximum value in the heap which is at index 0;
     * @return
     */
    public int getMinOrMax(){
        if(isEmpty()) throw new NoSuchElementException("Heap is empty");
        return heapArray[0];
    }

    /**
     * Inserts a new key into the heap.
     *
     * @param data The key to be inserted.
     */
    public void insert(int data){
        if(size >= capacity) return; // Do nothing when heap is full
        heapArray[size] = data; // Insert at the end.
        heapifyUp(size); // Check and update the heap if the Heap property is not met, starting from the bottom where the new node was inserted which is at the end of the Array
        size++;
    }

    /**
     * Deletes the key at a specified index in the heap.
     *
     * @param i The index of the key to be deleted.
     */
    public void deleteKey(int i)
    {
        swap(i, size-1); //Swap with last element in the heap and call heapifyDown at the deleted index
        size--;
        heapifyDown(i);
    }

    /**
     * Recursively calls itself bottom to top until whole heap is adjusted to satisfy the Heap property
     * When isMinHeap is true - Checks parent value and swaps if parent is greater than current index value and calls heapifyUp on the parent of the Tree.
     * When isMinHeap is false - Checks parent value and swaps if parent is lesser than current index value and calls heapifyUp on the parent of the Tree.
     * @param index
     */
    private void heapifyUp(int index){
        // Check if parent has greater, equal or less value than Node at given index and call recursively until we reach the front of the heap to ensure Heap property is satisfied.
        int parentIndex = getParentIndex(index);
        if(parentIndex >= 0){
            if(isMinHeap){
                if(heapArray[parentIndex] > heapArray[index]){
                    swap(index, parentIndex);
                    heapifyUp(parentIndex);
                }
            }else{
                if(heapArray[parentIndex] < heapArray[index]){
                    swap(index, parentIndex);
                    heapifyUp(parentIndex);
                }
            }

        }
    }

    private void swap(int i, int j) {
        int tempData = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = tempData;
    }

    /**
     * Recursively calls itself top to bottom until whole heap is adjusted to satisfy the Heap property.
     * When isMinHeap is true - Checks left and right child values and swaps with the lowest value and calls heapifyDown on that side of the Tree.
     * When isMinHeap is false - Checks left and right child values and swaps with the highest value and calls heapifyDown on that side of the Tree.
     * @param index
     */
    private void heapifyDown(int index){
        // Check if left or right has lesser/greater value and call recursively until we reach the front of the heap to ensure Heap property is satisfied.
        int leftIndex = getLeftIndex(index);
        int rightIndex = getRightIndex(index);
        int targetIndex = index;
        if(isMinHeap){
            if (leftIndex < size && heapArray[leftIndex] < heapArray[index]) {
                targetIndex = leftIndex;
            }
            if (rightIndex < size && heapArray[rightIndex] < heapArray[targetIndex]) {
                targetIndex = rightIndex;
            }
        }else{
            if (leftIndex < size && heapArray[leftIndex] > heapArray[index]) {
                targetIndex = leftIndex;
            }
            if (rightIndex < size && heapArray[rightIndex] > heapArray[targetIndex]) {
                targetIndex = rightIndex;
            }
        }
        if (targetIndex != index) {
            swap(index, targetIndex);
            heapifyDown(targetIndex);
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int getSize(){
        return size;
    }

    public static void main(String[] args) {
        // Create a BinaryHeap with a specific capacity
        BinaryHeap minHeap = new BinaryHeap(10);

        // Insert elements into the heap
        minHeap.insert(10);
        minHeap.insert(4);
        minHeap.insert(9);
        minHeap.insert(1);
        minHeap.insert(7);
        minHeap.insert(5);
        minHeap.insert(3);

        // Display the heap
        System.out.println("Min Heap after insertions:");
        printHeap(minHeap);

        // Extract the minimum element
        System.out.println("Extracted Min: " + minHeap.extractMinOrMax());

        // Display the heap after extraction
        System.out.println("Min Heap after extracting the minimum:");
        printHeap(minHeap);

        // Delete an element at index 2
        minHeap.deleteKey(2);
        System.out.println("Min Heap after deleting an element at index 2:");
        printHeap(minHeap);

        // Insert new element
        minHeap.insert(1);
        System.out.println("Min Heap after adding a new element:");
        printHeap(minHeap);

        // Create a Max BinaryHeap
        BinaryHeap maxHeap = new BinaryHeap(10, false);

        // Insert elements into the heap
        maxHeap.insert(10);
        maxHeap.insert(4);
        maxHeap.insert(9);
        maxHeap.insert(1);
        maxHeap.insert(7);
        maxHeap.insert(5);
        maxHeap.insert(3);

        // Display the heap
        System.out.println("Max Heap after insertions:");
        printHeap(maxHeap);

        // Extract the maximum element
        System.out.println("Extracted Max: " + maxHeap.extractMinOrMax());

        // Display the heap after extraction
        System.out.println("Max Heap after extracting the maximum:");
        printHeap(maxHeap);

        // Delete an element at index 2
        maxHeap.deleteKey(2);
        System.out.println("Max Heap after deleting an element at index 2:");
        printHeap(maxHeap);

        // Insert new element
        maxHeap.insert(10);
        System.out.println("Max Heap after adding a new element:");
        printHeap(maxHeap);
    }

    // Utility method to print the heap
    private static void printHeap(BinaryHeap heap) {
        for (int i = 0; i < heap.size; i++) {
            System.out.print(heap.heapArray[i] + " ");
        }
        System.out.println();
    }

}
