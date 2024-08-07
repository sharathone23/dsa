package nonlinear;
import java.util.*;

public class BinaryTree {
    Node root;
    public BinaryTree(Node root){
        this.root = root;
    }
    /**
     * Prints all the values of Nodes by Iterating through full tree in a Depth First Search - In Order Traversal( Left-Root-Right)
     */
    public void inOrderTraversal(){
        inOrder(this.root);
    }

    private void inOrder(Node node){
        if(node == null) return;
        inOrder(node.left);
        System.out.print(node.data +" ");
        inOrder(node.right);
    }

    /**
     * Prints all the values of Nodes by Iterating through full tree in a Depth First Search - Pre Order Traversal( Root-Left-Right)
     */
    public void preOrderTraversal(){
        preOrder(this.root);
    }

    private void preOrder(Node node){
        if(node == null) return;
        System.out.print(node.data +" ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * Prints all the values of Nodes by Iterating through full tree in a Depth First Search - In Order Traversal( Left-Right-Root)
     */
    public void postOrderTraversal(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data +" ");
    }

    /**
     * Prints all the nodes level by level in same line with a space
     */
    public void levelOrderTraversal(){
        if(root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            System.out.print(node.data +" ");
            if(node.left != null)queue.add(node.left);
            if(node.right != null)queue.add(node.right);
        }
    }

    /**
     * Prints all the nodes level by level in separate lines for each level
     */
    public void levelOrderTraversalLineByLine(){
        if(root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size(); // current queue size in current level
            for(int i=0;i<size;i++){
                Node node = queue.poll();
                System.out.print(node.data +" "); // prints in same line
                if(node.left != null)queue.add(node.left);
                if(node.right != null)queue.add(node.right);
            }
            System.out.println(); // Adds a new line after a level is processed
        }
    }

    /**
     * Prints all the nodes navigating level by level in spiral form in same line starting from root and right to left in next level, left to right in next level, etc.
     */
    public void levelOrderTraversalSpiralForm(){
        if(root == null) return;
        Deque<Node> dll = new ArrayDeque<>();
        dll.addLast(root);
        boolean fromRight = false;
        while(!dll.isEmpty()){
            int size = dll.size(); // current dll size in current level
            for(int i=0;i<size;i++){
                Node node = fromRight ? dll.removeLast() : dll.removeFirst();
                System.out.print(node.data +" "); // prints in same line
                if(fromRight){
                    if(node.right != null){
                        dll.addFirst(node.right);
                    }
                    if(node.left != null){
                        dll.addFirst(node.left);
                    }
                }else{
                    if(node.left != null){
                        dll.addLast(node.left);
                    }
                    if(node.right != null){
                        dll.addLast(node.right);
                    }

                }
            }
            fromRight = !fromRight;
        }
    }

    /**
     * Searches for a given value in the Tree by iterating all the nodes
     * @param value to be searched in Tree
     * @return Node which matches the given value Or null if Tree is null or not found.
     * Time Complexity - O(n)
     */
    public Node search(int value){
        if(this.root == null) return null;
        return find(value, root);
    }

    private Node find(int value, Node node){
        if(node == null) return null;
        if(node.data == value) return node;
        Node foundNodeLeftSide = find(value, node.left);
        if(foundNodeLeftSide != null) return foundNodeLeftSide;
        Node foundNodeRightSide = find(value, node.right);
        if(foundNodeRightSide != null) return foundNodeRightSide;
        return null;
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
     * Time Complexity - O(n)
     */
    public int getMin(){
        if(root == null) return -1;
        return getMinimumValue(root);
    }

    private int getMinimumValue(Node node){
        if(node == null) return Integer.MAX_VALUE; // Return max value for null to ensure it doesn't affect the min comparison
        int leftMin = getMinimumValue(node.left);
        int rightMin = getMinimumValue(node.right);
        return Math.min(node.data, Math.min(leftMin, rightMin));
    }

    /**
     * Method to calculate the maximum value present in the Tree
     * @return current maximum of the tree
     * Time Complexity - O(n)
     */
    public int getMax(){
        if(root == null) return -1;
        return getMaximumValue(root);
    }

    private int getMaximumValue(Node node){
        if(node == null) return Integer.MIN_VALUE; // Return min value for null to ensure it doesn't affect the max comparison
        int leftMax = getMaximumValue(node.left);
        int rightMax = getMaximumValue(node.right);
        return Math.max(node.data, Math.max(leftMax, rightMax));
    }

    /**
     * Inserts a new node with given value at the end of the BinaryTree. Uses Level Order Traversal(BFS) and finds the next available spot to make it a complete binary tree. 
     * @param value
     */
    public void insertLevelOrder(int value){
        Node node = new Node(value);
        if(root == null) {
            this.root = node;
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node currentNode = queue.poll();
            if(currentNode.left == null){
                currentNode.left = node;
                return;
            }else{
                queue.add(currentNode.left);
            }
            if(currentNode.right == null){
                currentNode.right = node;
                return;
            }else{
                queue.add(currentNode.right);
            }
        }
    }



    /**
     * Print the nodes at the given distance to Root, Example: level 1 prints the first level nodes which are left and right of root.
     */
    public void printNodesAtLevel(int level){
        if(level < 0 || root == null) return;
        if(level == 0){
            System.out.print(root.data);
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int currLevel = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                Node node = queue.poll();
                if(level == currLevel){
                    System.out.print(node.data + " ");
                }
                if(node.left != null)queue.add(node.left);
                if(node.right != null)queue.add(node.right);
            }
            currLevel++;
        }
    }

    /**
     * Prints left view of the BinaryTree
     * - Prints the first Node at each level in separate lines
     */
    public void printLeftView(){
        if(root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                Node node = queue.poll();
                if(i == 0){
                    System.out.print(node.data + " ");
                }
                if(node.left != null)queue.add(node.left);
                if(node.right != null)queue.add(node.right);
            }
            System.out.println();
        }
    }

    /**
     * Prints right view of the BinaryTree
     * - Prints the last Node at each level in separate lines
     */
    public void printRightView(){
        if(root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                Node node = queue.poll();
                if(i == size-1){
                    System.out.print(node.data + " ");
                }
                if(node.left != null)queue.add(node.left);
                if(node.right != null)queue.add(node.right);
            }
            System.out.println();
        }
    }

    /**
     * Prints top view of the BinaryTree
     * Prints the Nodes in same line separated by a space
     */
    public void printTopView(){

    }

    /**
     * Prints bottom view of the BinaryTree
     * Prints the Nodes in same line separated by a space
     */
    public void printBottomView(){

    }

    /**
     * Naive approach which requires multiple traversals to return Least Common Ancestor for the given two numbers present in the Tree
     * Builds two paths for two numbers from root to theat number and then iterates over those paths to find the last common node and returns it.
     * @param n1
     * @param n2
     * @return
     */
    public Node naiveLCA(int n1, int n2){
        if(root == null) return null;
        List<Node> n1Path = new ArrayList<>();
        List<Node> n2Path = new ArrayList<>();
        n1Path.add(root);
        n2Path.add(root);
        //Fill both paths for n1 and n2 starting from root.
        fillPath(n1Path, n1, root);
        fillPath(n2Path, n2, root);
        //Iterate both paths and find the last common node which is the lca for two given numbers n1 and n2.
        int i=0;
        while(i<n1Path.size() && i<n2Path.size()){
            if(n1Path.get(i) != n2Path.get(i)) break;
            i++;
        }
        return n1Path.get(i-1);
    }

    private void fillPath(List<Node> path, int n, Node node){
        if(node == null) return;
        if(isExists(n,node)){
            path.add(node);
        }
        fillPath(path,n,node.left);
        fillPath(path,n,node.right);
    }

    private boolean isExists(int n, Node node){
        if(node == null) return false;
        if(node.data == n) return true;
        return isExists(n, node.left) || isExists(n, node.right);
    }

    /**
     * Efficient Approach with single traversal
     * Returns the Least Common Ancestor for the given two numbers present in the Tree.
     * This assumes both n1 and n2 are present in the tree and doesn't return null when either one of them is not present
     * @param n1
     * @param n2
     * @return
     */
    public Node getLCA(int n1, int n2){
        return findLCA(root, n1, n2);
    }

    /**
     * Possible Scenarios
     * 1. If current node is either n1 or n2 then this is the LCA for those two numbers
     * 2. If leftLCA and rightLCA are not null, meaning n1 and n2 present on each side then return current node
     * 3. If either one of them is not null then that means both n1 and n2 are present on that side , so return the non-null side
     * 4. If both or null return null
     * @param node
     * @param n1
     * @param n2
     * @return
     */
    private Node findLCA(Node node, int n1, int n2){
        if(node == null) return null;
        if(node.data == n1 || node.data == n2) return node;
        Node leftLCA = findLCA(node.left, n1, n2);
        Node rightLCA = findLCA(node.right, n1, n2);
        if(leftLCA != null && rightLCA != null) return node;
        return leftLCA != null ? leftLCA : rightLCA;
    }


    Node prev = null; // global temp variable for storing a reference to prev while building a DLL from Tree
    Node head = null; // global variable for head of the DLL
    /**
     * Method to convert the BinaryTree to a DoublyLinkedList in the order of InOrder Traversal, Uses existing reference of Tree Node left and right for prev and next in DLL.
     * @return
     */
    public Node convertToDDL(){
        inOrderForDDL(root);
        return head;
    }

    private void inOrderForDDL(Node node){
        if(node == null) return;
        inOrderForDDL(node.left);
        if(prev == null){
            head = node;
        }else{
            node.left = prev;
            prev.right = node;
        }
        prev = node;
        inOrderForDDL(node.right);
    }

    int diameter = Integer.MIN_VALUE;
    /**
     * Returns the diameter which is the maximum distance in between any two nodes in the Tree
     * @return
     */
    public int getDiameter(){
        diameter = Integer.MIN_VALUE; // to reset for multiple calls on same instance of Tree
        calculateDiameter(root);
        return diameter;
    }

    private int calculateDiameter(Node node){
        if(node == null) return 0;
        int leftHeight = calculateDiameter(node.left);
        int rightHeight = calculateDiameter(node.right);
        diameter =  Math.max(diameter, 1 + leftHeight + rightHeight);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public Node getSuccessorOfInOrderTraversal(Node node){
        if(node == null) return null;
        if(node.right != null){
            return getFirstInInorderTraverse(node.right);
        }else{
            // traverse up the tree until we find a node where its parent.left == itself (node.left.parent = node) => return node
            Node parent = node.parent;
            while(parent!=null && parent.left != node){
                node = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    public Node getPredecessorOfInOrderTraversal(Node node){
        if(node == null) return null;
        if(node.left != null){
            return getLastInInorderTraverse(node.left);
        }else{
            // traverse up the tree until we find a node where its parent.right == itself ( node.right.parent == node)
            Node parent = node.parent;
            while(parent!=null && parent.right != node){
                node = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    public Node getFirstInInorderTraverse(Node node){
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Node getLastInInorderTraverse(Node node){
        if (node == null) return null;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * Inserts given newNode after node - as per In Order traversal
     * @param node - non null
     * @param newNode - non null
     *                returns true if insert is success - false otherwise
     */
    public boolean subTreeInsertAfter(Node node, Node newNode){
        if(node == null || newNode == null) return false;
        if(node.right ==null){
            node.right = newNode;
            newNode.parent = node;
        }else if(node.right!=null){
            // insert left of getFirstInInorderTraverse(node.right)
            Node leftMostNode = getFirstInInorderTraverse(node.right);
            leftMostNode.left = newNode;
            newNode.parent = leftMostNode;
        }
        return true;
    }

    /**
     * Inserts given newNode before node - as per In Order traversal
     * @param node - non null
     * @param newNode - non null
     *                returns true if insert is success - false otherwise
     */
    public boolean subTreeInsertBefore(Node node, Node newNode){
        if(node == null || newNode == null) return false;
        if(node.left ==null){
            node.left = newNode;
            newNode.parent = node;
        }else if(node.left!=null){
            // insert right of getLastInInorderTraverse(node.right)
            Node rightMostNode = getLastInInorderTraverse(node.left);
            rightMostNode.right = newNode;
            newNode.parent = rightMostNode;
        }
        return true;
    }


    /**
     * deletes given node by preserving the inorder traversal
     * @param node - non null
     */
    public boolean delete(Node node){
        if(node == null) return false;
        if(node.left == null && node.right == null){
            // detach from parent
            if(node.parent.left == node) node.parent.left = null;
            if(node.parent.right == node) node.parent.right = null;
        }else if(node.left!=null){
            // swap with predecessor of node and recurse delete on swapped node
            Node predecessorNode = getPredecessorOfInOrderTraversal(node);
            int temp = predecessorNode.data;
            predecessorNode.data = node.data;
            node.data = temp;
            delete(predecessorNode);
        }else{
            // swap with successor of node and recurse delete on swapped node
            Node successorNode = getSuccessorOfInOrderTraversal(node);
            int temp = successorNode.data;
            successorNode.data = node.data;
            node.data = temp;
            delete(successorNode);
        }
        return true;
    }


    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(new Node(1));
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
        System.out.println();
        System.out.println("-- inOrderTraversal --");
        tree.inOrderTraversal();
        System.out.println();

        System.out.println("-- preOrderTraversal --");
        tree.preOrderTraversal();
        System.out.println();

        System.out.println("-- postOrderTraversal --");
        tree.postOrderTraversal();
        System.out.println();

        System.out.println("-- levelOrderTraversal --");
        tree.levelOrderTraversal();
        System.out.println();

        System.out.println("-- levelOrderTraversalLineByLine --");
        tree.levelOrderTraversalLineByLine();

        System.out.println("-- levelOrderTraversalSpiralForm -- ");
        tree.levelOrderTraversalSpiralForm();
        System.out.println();

        System.out.println("-- getHeight -- ");
        System.out.println(tree.getHeight());

        System.out.println("-- getMin -- ");
        System.out.println(tree.getMin());

        System.out.println("-- getMax -- ");
        System.out.println(tree.getMax());

        System.out.println("-- printLeftView -- ");
        tree.printLeftView();

        System.out.println("-- printRightView -- ");
        tree.printRightView();

        System.out.println("-- getDiameter()");
        System.out.println(tree.getDiameter());



        Node root = new Node(20);
        Node node10 = new Node(10);
        Node node30 = new Node(30);
        Node node5 = new Node(5);
        Node node15 = new Node(15);
        Node node25 = new Node(25);
        Node node35 = new Node(35);

        // Connect nodes to form the tree
        root.left = node10;
        root.right = node30;
        node10.parent = root;
        node30.parent = root;

        node10.left = node5;
        node10.right = node15;
        node5.parent = node10;
        node15.parent = node10;

        node30.left = node25;
        node30.right = node35;
        node25.parent = node30;
        node35.parent = node30;

        BinaryTree bTreeWithParent = new BinaryTree(root);
        BinaryTree.printTreeHelper(root, 0);
        Node successor = bTreeWithParent.getSuccessorOfInOrderTraversal(node10);
        System.out.println("Successor of node 10: " + (successor != null ? successor.data: "null"));

        successor = bTreeWithParent.getSuccessorOfInOrderTraversal(node5);
        System.out.println("Successor of node 5: " + (successor != null ? successor.data : "null"));

        successor = bTreeWithParent.getSuccessorOfInOrderTraversal(bTreeWithParent.root);
        System.out.println("Successor of root node 20: " + (successor != null ? successor.data : "null"));

        successor = bTreeWithParent.getSuccessorOfInOrderTraversal(node35);
        System.out.println("Successor of node 35: " + (successor != null ? successor.data : "null"));

        successor = bTreeWithParent.getSuccessorOfInOrderTraversal(node15);
        System.out.println("Successor of node 15: " + (successor != null ? successor.data : "null"));

        Node predecessor = bTreeWithParent.getPredecessorOfInOrderTraversal(node10);
        System.out.println("predecessor of node 10: " + (predecessor != null ? predecessor.data: "null"));

        predecessor = bTreeWithParent.getPredecessorOfInOrderTraversal(bTreeWithParent.root);
        System.out.println("predecessor of node 20(root): " + (predecessor != null ? predecessor.data : "null"));

        predecessor = bTreeWithParent.getPredecessorOfInOrderTraversal(node25);
        System.out.println("predecessor of node 25: " + (predecessor != null ? predecessor.data : "null"));

        bTreeWithParent.subTreeInsertAfter(node35, new Node(40));
        bTreeWithParent.subTreeInsertAfter(root, new Node(24));

        System.out.println("After 40 InsertAfter 35 && 24 InsertAfter root(20)");
        BinaryTree.printTreeHelper(root, 0);

        bTreeWithParent.subTreeInsertBefore(node5, new Node(50));
        bTreeWithParent.subTreeInsertBefore(root, new Node(55));

        System.out.println("After 50 Insertbefore 5 && 55 insertbefore root(20)");
        BinaryTree.printTreeHelper(bTreeWithParent.root, 0);

        bTreeWithParent.delete(node10);

        System.out.println("After deleting node 10");
        BinaryTree.printTreeHelper(bTreeWithParent.root, 0);

        bTreeWithParent.delete(root);

        System.out.println("After deleting node 20 (root)");
        BinaryTree.printTreeHelper(bTreeWithParent.root, 0);

    }


    //Prints root first as leftmost and righSubtree at top of root and leftsubtree as down of root
    private static void printTreeHelper(Node node, int level) {
        if (node == null) {
            return;
        }

        // Print right subtree first (this will appear at the top when printed)
        printTreeHelper(node.right, level + 1);

        // Print the current node with appropriate spacing
        printSpaces(4 * level);
        System.out.println(node.data);

        // Print left subtree
        printTreeHelper(node.left, level + 1);
    }

    private static void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }


}

class Node{
    Node left;
    Node right;
    Node parent;
    int data;
    public Node(int value){
        this.data = value;
    }
}
