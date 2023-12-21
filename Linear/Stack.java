package Linear;
public class Stack {

    LinkedList linkedList = new LinkedList();
    int size = 0;

    void push(int value){
        linkedList.insertFirst(value);
        size++;
    }

    int pop(){
        if(isEmpty()) throw new EmptyStackException("Cannot pop from an empty stack.");
        int deletedNodeValue = linkedList.head.data;
        linkedList.deleteFirst();
        size --;
        return deletedNodeValue;
    }

    int peek(){
        if(isEmpty()) throw new EmptyStackException("Cannot peek from an empty stack.");
        return linkedList.head.data;
    }

    boolean isEmpty(){
        return linkedList.head == null;
    }

    int size(){
        return size;
    }
    
}

class EmptyStackException extends RuntimeException {
    public EmptyStackException(String message) {
        super(message);
    }
}
