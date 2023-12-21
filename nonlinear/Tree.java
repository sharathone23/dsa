package NonLinear;
import java.util.*;
public class Tree {
    Node root;
    public Tree(Node root){
        this.root = root;
    }
    /**
     * Prints all the values of Nodes by Iterating through full tree in a Depth First Search.
     */
    public void printAllNodes(){
        print(this.root);
    }

    private void print(Node node){
        if(node == null) return;
        System.out.println(node.data + " ");
        for(Node childNode: node.children){
            print(childNode);
        }
    }

    /**
     * Searches for a given value in the Tree by iterating all the nodes
     * @param value to be searched in Tree
     * @return Node which matches the given value Or null if Tree is null or not found.
     */
    public Node search(int value){
        if(this.root == null) return null;
        return find(value, root);
    }

    private Node find(int value, Node node){
        if(node == null) return null;
        if(node.data == value) return node;
        for(Node childNode: node.children){
            Node foundNode = find(value, childNode);
            if(foundNode != null) return foundNode;
        }
        return null;
    }
}

class Node{
    List<Node> children;
    int data;
    public Node(int value){
        this.data = value;
        this.children = new ArrayList<>();
    }
    public Node(int value, List<Node> children){
        this.data = value;
        this.children = children != null ? children : new ArrayList<>();
    }
    /**
     * Adds a new Node to its children with given value as data
     * @param value
     */
    public void add(int value){
        Node node = new Node(value);
        if(this.children == null){
            this.children = new ArrayList<>();
        }else{
            this.children.add(node);
        }
    }
}
