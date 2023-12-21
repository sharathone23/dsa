package nonlinear;
public class BinarySearchTree extends BinaryTree {
    Node root;
    public BinarySearchTree(Node root){
        super(root);
    }
   
    // All Traversals in DFS and BFS extends from super class BinaryTree and available in this class


    /**
     * Searches for a given value in the BinarySearchTree by using the BST property - root is greater than left and less than right
     * @param value to be searched in Tree
     * @return Node which matches the given value Or null if Tree is null or not found.
     * Time Complexity - O(logn) - Binary Search
     */
    public Node search(int value){
        if(this.root == null) return null;
        return find(value, root);
    }

    private Node find(int value, Node node){
        if(node == null) return null;
        if(node.data == value) return node;
        if(node.data > value){
            //search left
            return find(value, node.left);
        }else{
            //search right
            return find(value, node.right);
        }
    }

    /**
     * Method to calculate the height of the Tree
     * @return current height of the tree
     */
    public int getHeight(){
        return maxHeight(root);
    }

    private int maxHeight(Node node){
        if(node == null) return 0;
        return 1 + Math.max(maxHeight(node.left), maxHeight(node.right));
    }

    /**
     * Method to calculate the minimum value present in the Tree
     * @return current minimum of the tree
     * Time Complexity - O(h) where h is the height of the BinaryTree
     */
    public int getMin(){
        if(root == null) return -1;
        Node currNode = root;
        Node leftNode = root.left;
        while(leftNode != null){
            currNode = leftNode;
            leftNode = leftNode.left;
        }
        return currNode.data;
    }

    /**
     * Method to calculate the maximum value present in the Tree
     * @return current maximum of the tree
     * Time Complexity - O(h) where h is the height of the BinaryTree
     */
    public int getMax(){
        if(root == null) return -1;
        Node currNode = root;
        Node rightNode = root.right;
        while(rightNode != null){
            currNode = rightNode;
            rightNode = rightNode.right;
        }
        return currNode.data;
    }

    /**
     * Inserts a new node with given value at the correct place in the BinarySearchTree. Uses Binary Search.
     * @param value
     */
    public void insert(int value){
        Node node = new Node(value);
        if(root == null) {
            this.root = node;
            return;
        }
        Node parent =  findParentForInsert(value, root);
        if(parent != null) { // This should never be null for non empty tree which is already checked above
            if(parent.data < value){
                parent.right = node;
            }else if(parent.data > value){
                parent.left = node;
            }
            // Ignore duplicate if already exists
        }
    }

    private Node findParentForInsert(int value, Node node){
        if(node == null) return null;
        if(node.data >= value){
            //search left
            Node parentNode = findParentForInsert(value, node.left);
            if(parentNode == null) return node; // No left
        }else{
            //search right
            Node parentNode = findParentForInsert(value, node.right);
            if(parentNode == null) return node; // No right
        }
        return  null;
    }


    /**
     * TODO
     * Deletes a node with given value. 
     * @param value
     */
    public void delete(int value){
       /**
        Deleting a node in a BST can be one of the more complex operations, mainly because it needs to account for different scenarios while maintaining the BST properties. Here are the common scenarios we need to handle:

        Node with No Children (Leaf Node): Simply remove the node.

        Node with One Child: Replace the node with its child.

        Node with Two Children: Find the node's in-order successor (the smallest node in its right subtree) or in-order predecessor (the largest node in its left subtree), copy its value to the node, and then delete the in-order successor or predecessor.
       */     
    }

}

class Node{
    Node left;
    Node right;
    int data;
    Node(int value){
        this.data = value;
    }
}
