package nonlinear;
import java.util.*;
public class Tree {
    TNode root;
    public Tree(TNode root){
        this.root = root;
    }
    /**
     * Prints all the values of Nodes by Iterating through full tree in a Depth First Search.
     */
    public void printAllNodes(){
        print(this.root);
    }

    private void print(TNode node){
        if(node == null) return;
        System.out.println(node.data + " ");
        for(TNode childNode: node.children){
            print(childNode);
        }
    }

    /**
     * Searches for a given value in the Tree by iterating all the nodes
     * @param value to be searched in Tree
     * @return TNode which matches the given value Or null if Tree is null or not found.
     */
    public TNode search(int value){
        if(this.root == null) return null;
        return find(value, root);
    }

    private TNode find(int value, TNode node){
        if(node == null) return null;
        if(node.data == value) return node;
        for(TNode childNode: node.children){
            TNode foundNode = find(value, childNode);
            if(foundNode != null) return foundNode;
        }
        return null;
    }
}

class TNode{
    List<TNode> children;
    int data;
    public TNode(int value){
        this.data = value;
        this.children = new ArrayList<>();
    }
    public TNode(int value, List<TNode> children){
        this.data = value;
        this.children = children != null ? children : new ArrayList<>();
    }
    /**
     * Adds a new TNode to its children with given value as data
     * @param value
     */
    public void add(int value){
        TNode node = new TNode(value);
        if(this.children == null){
            this.children = new ArrayList<>();
        }else{
            this.children.add(node);
        }
    }
}
