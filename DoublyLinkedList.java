public class DoublyLinkedList{
    Node head;
    Node tail; // Additional reference to tail, this improved the insert operation from O(n) to O(1).
    
    // Inserts new node at the end of the Linked List
    //O(1)
    void insert(int value){
        this.insertLast(value);
    }

    //O(1)
    void insertLast(int value){
        Node newItem = new Node(value);
        if(head == null){
            head = newItem;
            tail = newItem;
        } else{
            newItem.prev = tail;
            tail.next = newItem;
            tail = newItem;
        }
    }

    //O(1)
    void insertFirst(int value){
        Node newItem = new Node(value);
        if(head == null){
            head = newItem;
            tail = newItem;
        } else {
            newItem.next = head;
            head.prev = newItem; // Link back from the old head to the new head
            head = newItem;
        }
    }

    //O(1)
    boolean deleteFirst(){
        if(head == null) return false;
        if(head == tail){
            head = null;
            tail = null;
        }else{
            head = head.next;
            head.prev = null;
        }
        return true;
    }

    //O(1) uding tail reference
    boolean deleteLast(){
        if(head == null) return false;
        if(head == tail){
            head = null;
            tail = null;
        }else{
            tail = tail.prev;
            tail.next = null;
        }
        return true;
    }

    // Iterates over the Doubly Linked List sequentially and returns the node if the item data equals node data. Returns null if not found.
    Node find(int data){
        if(head == null) return null;
        Node current = head;
        while(current != null){
            if(current.data == data) return current;
            current = current.next;
        }
        return null;
    }

    // Returns true if the delete of the given data node is successfull else false.
    boolean delete(int data){
        if(head == null) return false;
        Node current = head;
        while(current != null){
            if(current.data == data){
                if(current.prev !=null){
                    current.prev.next = current.next;
                     if(current.next != null) {
                        current.next.prev = current.prev;
                    } else {
                        tail = current.prev; // Update tail if last node is deleted
                    }
                }else{
                    head = head.next;// Move head to the next node
                    if(head != null){
                        head.prev = null; // Update prev of the new head
                    } else {
                        tail = null; // If list becomes empty
                    }
                }
                return true;// Deletion successful
            }
            current = current.next;
        }
        return false; // Data not found in the list
    }
}

class Node{
   int data;
   Node next;
   Node prev;

   Node(int data) {
    this.data = data;
    this.next = null;
    this.prev = null;
   }
}