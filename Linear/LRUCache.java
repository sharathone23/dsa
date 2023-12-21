package linear;
import java.util.*;
public class LRUCache {
    HashMap<Integer, DNode> nodeReferenceMap;
    DoublyLinkedList cache;
    int capacity;

    LRUCache(int capacity){
        this.cache = new DoublyLinkedList();
        this.capacity = capacity;
        this.nodeReferenceMap = new HashMap<>(); 
    }

    int get(int key){
        if(nodeReferenceMap.containsKey(key)){
            DNode currentItem = nodeReferenceMap.get(key);
            remove(currentItem); // we can use delete from DoubleLinkedList but its O(n). this is O(1) since we have the reference to node to be deleted
            moveToFront(currentItem); // we can't use the insertFirst since it will create a new reference which is reduntant.
            return cache.head.data;
        }
        return -1;
    }

    void set(int key, int value){
       if(nodeReferenceMap.containsKey(key)){
            DNode node = nodeReferenceMap.get(key);
            //refresh value and move it to front
            node.data = value;
            //Move to front and make it head
            moveToFront(node);
        }else{
            if(nodeReferenceMap.size() == capacity){
                //delete from map and remove last item from cache
                nodeReferenceMap.remove(cache.tail.key);
                //remove tail
                cache.deleteLast();
            }
            //add new item at front of cache and add it to map.
            nodeReferenceMap.put(key, cache.insertFirst(key, value)); 
        }
    }

    void moveToFront(DNode node){
        if(nodeReferenceMap.size() == 1) return;
        if(cache.tail == node) { // When node to be moved is a current tail. Update tail
            cache.tail = cache.tail.prev;
            cache.tail.next = null;
        }
        if(cache.head == node){// When node to be moved is head, do nothing as node is already at front
            return;
        }
        cache.head.prev = node;
        node.next = cache.head;
        cache.head = node;
    }

    void remove(DNode node){
      if(nodeReferenceMap.size() ==1) return;
      if(cache.tail == node) { // When node to be removed is a current tail. Update tail
        cache.tail = cache.tail.prev;
        cache.tail.next = null;
        return;
      }
      if(cache.head == node){// When node to be removed is head, Update head
        cache.head = node.next;
        cache.head.prev = null;
        return;
      }
      DNode prev_node = node.prev;
      DNode next_node = node.next;
      prev_node.next = next_node;
      next_node.prev = prev_node;
    }

}