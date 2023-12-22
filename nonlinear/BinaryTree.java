package nonlinear;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
    public void postOrderTraversal(Node node){
        postOrder(node);
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
        if(node.left == null && node.right == null) return node.data;
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
        if(node.left == null && node.right == null) return node.data;
        int leftMax = getMaximumValue(node.left);
        int rightMax = getMaximumValue(node.right);
        return Math.max(node.data, Math.max(leftMax, rightMax));
    }

    /**
     * Inserts a new node with given value at the end of the BinaryTree. Uses Level Order Traversal(BFS) and finds the next available spot to make it a complete binary tree. 
     * @param value
     */
    public void insert(int value){
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
     * Deletes a node with given value. If deleted node is not a leaf i.e a parent. It makes the last node of tree to be the new parent to make the tree complete
     * @param value
     */
    public void delete(int value){
        if(root == null) {
            return;
        }
        Node nodeToBeDeleted = null;
        Node lastNode = null; // This will store the lastNode when below while loop ends
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            lastNode = queue.poll();
            if(lastNode.data == value){
                nodeToBeDeleted = lastNode; // we let the loop run here by not adding a break; to find the lastNode of the Tree
            }else{
                if(lastNode.left != null) queue.add(lastNode.left);
                if(lastNode.right != null)queue.add(lastNode.right);
            }
        }
        if(nodeToBeDeleted != null) {
            nodeToBeDeleted.data = lastNode.data; // override the data in nodeToBeDeleted with lastNodes value 
            deleteNode(lastNode);//delete lastNodes reference from its parent(left/right).
        }
    }

    //Deletes the given node from Tree - by removing the reference from its parent(left/right) and marking as null
    private void deleteNode(Node node) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            if (temp.left != null) {
                if (temp.left == node) {
                    temp.left = null;
                    return;
                } else {
                    queue.add(temp.left);
                }
            }

            if (temp.right != null) {
                if (temp.right == node) {
                    temp.right = null;
                    return;
                } else {
                    queue.add(temp.right);
                }
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


}

class Node{
    Node left;
    Node right;
    int data;
    public Node(int value){
        this.data = value;
    }
}
