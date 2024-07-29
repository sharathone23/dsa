package linear;
public class LinkedList{
    Node head;
    Node tail; // Additional reference to tail, this improved the insert operation from O(n) to O(1).
    
    // Inserts new node at the end of the Linked List - O(1)
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
        } else{
            newItem.next = head;
            head = newItem;
        }
    }

    //O(1)
    boolean deleteFirst(){
        if(head == null) return false; //Empty list
        if(head == tail){ // single node
            head = null;
            tail = null;
        }else{
            head = head.next;
        }
        return true;
    }

    //O(n)
    boolean deleteLast(){
        if(head == null) return false; // Empty list
        if(head == tail){ // single node
            head = null;
            tail = null;
        }else{
            Node current = head;
            Node prev = null;
            while(current.next != null){
                prev = current;
                current = current.next;
            }
            tail = prev;
            prev.next = null;
        }
        return true;
    }

    // Iterates over the Linked List sequentially and returns the node if the item data equals node data. Returns null if not found.
    //O(n)
    Node find(int data){
        if(head == null) return null;
        Node current = head;
        while(current != null){
            if(current.data == data) return current;
            current = current.next;
        }
        return null;
    }

    // Returns true if the delete is successfull else false.
    //O(n)
    boolean delete(int data){
        if(head == null) return false;
        Node current = head;
        Node prev = null;
        while(current != null){
            if(current.data == data){
                if(prev !=null){
                    prev.next = current.next;
                    if(prev.next == null) tail = prev;// Update tail if last node is deleted
                }else{
                    head = head.next;// Move head to the next node
                    if(head == null){
                        tail = null;// If list becomes empty
                    }
                }
                return true;// Deletion successful
            }else{
                prev = current;
                current = current.next;
            }
        }
        return false; // Data not found in the list
    }

    void print(){
        Node current = head;
        while(current != null){
            System.out.print(current.data +" ");
            current = current.next;
        }
        System.out.println();
        System.out.println("Current Head "+head.data);
        System.out.println("Current Tail "+tail.data);
    }

    void reverse(){
        Node prev = null;
        Node current = head;
        Node oldHead = head;
        while(current != null){
            Node temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        head = prev;  // Update head to the new front of the list
        tail = oldHead;
    }

    public static void main(String args[]){
        LinkedList ll = new LinkedList();
        ll.insertLast(1);
        ll.insertLast(2);
        ll.insertLast(3);
        ll.insertLast(4);
        ll.insertLast(5);
        ll.print();
        ll.reverse();
        ll.print();
    }
}

class Node{
   int data;
   Node next;
   Node(int data) {
    this.data = data;
    this.next = null;
   }
}