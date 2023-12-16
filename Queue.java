public class Queue {

    LinkedList linkedList = new LinkedList();
    int size = 0;

    void enqueue(int value){
        linkedList.insertLast(value);
        size ++;
    }

    int dequeue(){
        if(isEmpty()) throw new EmptyQueueException("Cannot dequeue from an empty queue.");
        int deletedNodeValue = linkedList.head.data;
        linkedList.deleteFirst();
        size--;
        return deletedNodeValue;
    }

    int peek(){
        if(isEmpty()) throw new EmptyQueueException("Cannot peek from an empty queue.");
        return linkedList.head.data;
    }

    boolean isEmpty(){
        return linkedList.head == null;
    }

    int size(){
        return size;
    }
    
}

class EmptyQueueException extends RuntimeException {
    public EmptyQueueException(String message) {
        super(message);
    }
}
