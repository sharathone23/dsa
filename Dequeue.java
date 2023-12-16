public class Dequeue {

    DoublyLinkedList doublyLinkedList = new DoublyLinkedList(); // to achieve O(1) for all ops including removeLast which is O(n) in a SinglyLinkedList
    int size = 0;

    void insertFirst(int value){
        doublyLinkedList.insertFirst(value);
        size ++;
    }

    void insertLast(int value){
        doublyLinkedList.insertLast(value);
        size ++;
    }

    int removeFirst(){
        if(isEmpty()) throw new EmptyDequeueException("Cannot remove an item from an empty Dequeue.");
        int deletedNodeValue = doublyLinkedList.head.data;
        doublyLinkedList.deleteFirst();
        size--;
        return deletedNodeValue;
    }

     int removeLast(){
        if(isEmpty()) throw new EmptyDequeueException("Cannot remove an item from an empty Dequeue.");
        int deletedNodeValue = doublyLinkedList.tail.data;
        doublyLinkedList.deleteLast();
        size--;
        return deletedNodeValue;
    }

    int peekFirst(){
        if(isEmpty()) throw new EmptyDequeueException("Cannot peek from an empty Dequeue.");
        return doublyLinkedList.head.data;
    }

    int peekLast(){
        if(isEmpty()) throw new EmptyDequeueException("Cannot peek from an empty Dequeue.");
        return doublyLinkedList.tail.data;
    }

    boolean isEmpty(){
        return size == 0;
    }

    int size(){
        return size;
    }
    
}

class EmptyDequeueException extends RuntimeException {
    public EmptyDequeueException(String message) {
        super(message);
    }
}
