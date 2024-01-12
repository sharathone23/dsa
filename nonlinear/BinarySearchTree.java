package nonlinear;
public class BinarySearchTree extends BinaryTree {
    Node root;

    public BinarySearchTree(Node root) {
        super(root);
    }

    // All Traversals in DFS and BFS extends from super class BinaryTree and available in this class


    /**
     * Searches for a given value in the BinarySearchTree by using the BST property - root is greater than left and less than right
     *
     * @param value to be searched in Tree
     * @return Node which matches the given value Or null if Tree is null or not found.
     * Time Complexity - O(h) , O(logn) if we maintain a Balanced Search Tree for every insert and delete like AVL and Red Black Trees
     */
    public Node search(int value) {
        if (this.root == null) return null;
        return findIterative(value, root);
    }

    // Recursive find - Space Complexity - O(h)
    private Node find(int value, Node node) {
        if (node == null) return null;
        if (node.data == value) return node;
        if (node.data > value) {
            //search left
            return find(value, node.left);
        } else {
            //search right
            return find(value, node.right);
        }
    }

    // Iterative find - Space Complexity - O(1)
    private Node findIterative(int value, Node node) {
        if (node == null) return null;
        while(node != null){
            if(node.data == value) return node;
            if(node.data < value){
                node = node.right;
            }else{
                node = node.left;
            }
        }
        return null;
    }

    /**
     * Method to calculate the height of the Tree
     *
     * @return current height of the tree
     */
    public int getHeight() {
        return maxHeight(root);
    }

    private int maxHeight(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(maxHeight(node.left), maxHeight(node.right));
    }

    /**
     * Method to calculate the minimum value present in the Tree
     *
     * @return current minimum of the tree
     * Time Complexity - O(h) where h is the height of the BinaryTree
     */
    public int getMin() {
        if (root == null) return -1;
        Node currNode = root;
        Node leftNode = root.left;
        while (leftNode != null) {
            currNode = leftNode;
            leftNode = leftNode.left;
        }
        return currNode.data;
    }

    /**
     * Method to calculate the maximum value present in the Tree
     *
     * @return current maximum of the tree
     * Time Complexity - O(h) where h is the height of the BinaryTree
     */
    public int getMax() {
        if (root == null) return -1;
        Node currNode = root;
        Node rightNode = root.right;
        while (rightNode != null) {
            currNode = rightNode;
            rightNode = rightNode.right;
        }
        return currNode.data;
    }

    /**
     * Inserts a new node with given value at the correct place in the BinarySearchTree. Uses Binary Search.
     * Time Complexity - O(h), O(log n) if we maintain a Balanced Search Tree for every insert and delete like AVL and Red Black Trees
     * Space Complexity - O(h)
     * @param value
     */
    public void insert(int value) {
        Node node = new Node(value);
        if (root == null) {
            this.root = node;
            return;
        }
        Node parent = findParentForInsert(value, root);
        if (parent != null) { // This should never be null for non-empty tree which is already checked above
            if (parent.data < value) {
                parent.right = node;
            } else if (parent.data > value) {
                parent.left = node;
            }
            // Ignore duplicate if already exists
        }
    }

    /**
     * Inserts a new node with given value at the correct place in the BinarySearchTree Iteratively. Uses Binary Search.
     * Time Complexity - O(h), O(log n) if we maintain a Balanced Search Tree for every insert and delete like AVL and Red Black Trees
     * Space Complexity - O(1)
     *
     * @param value
     */
    public void insertIterative(int value){
        Node newNode = new Node(value);
        if (root == null){
            this.root = newNode;
            return;
        }
        Node parent = null;
        Node current = root;
        while(current != null){
            parent = current;
            if(current.data == value) return; // Ignore if Node with value already exists
            if(current.data < value){
                current = current.right;
            }else{
                current = current.left;
            }
        }
        if(parent.data < value){
            parent.right = newNode;
        }else{
            parent.left = newNode;
        }
    }

    private Node findParentForInsert(int value, Node node) {
        if (node == null) return null;
        if (node.data >= value) {
            //search left
            Node parentNode = findParentForInsert(value, node.left);
            if (parentNode == null) return node; // No left
        } else {
            //search right
            Node parentNode = findParentForInsert(value, node.right);
            if (parentNode == null) return node; // No right
        }
        return null;
    }


    /**
     * Deletes a node with given value.
     *
     * @param value
     */
    public void delete(int value) {
        /**
         Deleting a node in a BST can be one of the more complex operations, mainly because it needs to account for different scenarios while maintaining the BST properties. Here are the common scenarios we need to handle:

         Node with No Children (Leaf Node): Simply remove the node.

         Node with One Child: Replace the node with its child.

         Node with Two Children: Find the node's in-order successor (the smallest node in its right subtree) or in-order predecessor (the largest node in its left subtree), copy its value to the node, and then delete the in-order successor or predecessor.
         */
        delNode(root, value);
    }

    private Node delNode(Node root, int value){
        if(root == null) return null;
        if(root.data < value){
            root.right = delNode(root.right, value);
        }else if(root.data > value){
            root.left = delNode(root.left, value);
        } else {
            if(root.right == null) return root.left;
            if(root.left == null) return root.right;
            Node succesorNode = getInOderSuccessorForDelete(root);
            delNode(succesorNode,value); // deletes the successor node as this is moved to current root
            root.data = succesorNode.data;
        }
        return root;
    }

    private Node getInOderSuccessorForDelete(Node root){
        Node curr = root.right;
        while(curr != null && curr.left != null){
            curr = curr.left;
        }
        return curr;
    }

}
