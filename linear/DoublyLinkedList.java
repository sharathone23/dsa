package linear;
public class DoublyLinkedList{
    DNode head;
    DNode tail; // Additional reference to tail, this improved the insert operation from O(n) to O(1).
    
    // Inserts new node at the end of the Linked List
    //O(1)
    DNode insert(int value){
        return this.insertLast(value);
    }

    // Inserts new node with given key, value at the end of the Linked List
    //O(1)
    DNode insert(int key, int value){
        return this.insertLast(key, value);
    }

    //O(1)
    DNode insertLast(int value){
        DNode newItem = new DNode(value);
        if(head == null){
            head = newItem;
            tail = newItem;
        } else{
            newItem.prev = tail;
            tail.next = newItem;
            tail = newItem;
        }
        return newItem;
    }

     //O(1)
     DNode insertLast(int key, int value){
         DNode newItem = new DNode(key, value);
        if(head == null){
            head = newItem;
            tail = newItem;
        } else{
            newItem.prev = tail;
            tail.next = newItem;
            tail = newItem;
        }
        return newItem;
    }

    //O(1)
    DNode insertFirst(int value){
        DNode newItem = new DNode(value);
        if(head == null){
            head = newItem;
            tail = newItem;
        } else {
            newItem.next = head;
            head.prev = newItem; // Link back from the old head to the new head
            head = newItem;
        }
        return newItem;
    }

    //O(1)
    DNode insertFirst(int key, int value){
        DNode newItem = new DNode(key, value);
        if(head == null){
            head = newItem;
            tail = newItem;
        } else {
            newItem.next = head;
            head.prev = newItem; // Link back from the old head to the new head
            head = newItem;
        }
        return newItem;
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
    DNode find(int data){
        if(head == null) return null;
        DNode current = head;
        while(current != null){
            if(current.data == data) return current;
            current = current.next;
        }
        return null;
    }

    // Returns true if the delete of the given data node is successfull else false.
    boolean delete(int data){
        if(head == null) return false;
        DNode current = head;
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

    void print(){
        DNode current = head;
        while(current !=null){
            System.out.print(current.data +" ");
            current = current.next;
        }
        System.out.println();
        System.out.println("Current Head "+head.data);
        System.out.println("Current Tail "+tail.data);
    }

    void reverse(){
        DNode prev = null;
        DNode current = head;
        DNode oldHead = head;
        while(current != null){
            DNode temp = current.next;
            current.next = prev;
            current.prev = temp;
            prev = current;
            current = temp;
        }
        head = prev;
        tail = oldHead;
    }

    public static void main(String args[]){
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.insertLast(1);
        dll.insertLast(2);
        dll.insertLast(3);
        dll.insertLast(4);
        dll.insertLast(5);
        dll.print();
        dll.reverse();
        dll.print();
    }
}

class DNode{
   int key; //used when key value pair is required like in a LRU implementaion
   int data;
   DNode next;
   DNode prev;

    DNode(int data) {
    this.key = data;
    this.data = data;
    this.next = null;
    this.prev = null;
   }

    DNode(int key, int data) {
    this.key = key;
    this.data = data;
    this.next = null;
    this.prev = null;
   }
}