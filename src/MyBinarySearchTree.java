public class MyBinarySearchTree<T extends Comparable<T>>{
    private Node root;
    private int size;
    public Integer rotations;
    private boolean balancing;
    public long comparisons;

    public MyBinarySearchTree() {
        root = null;
        size = 0;
        comparisons = 0;
        rotations = 0;
        balancing = false;
    }

    public MyBinarySearchTree(boolean balancing) {
        root = null;
        size = 0;
        comparisons = 0;
        rotations = 0;
        this.balancing = balancing;
    }

    public void add(T item){
        this.root = add(item, root);
        size++;

    }

    private Node add(T item, Node subTree){
        if (subTree == null) {
            return new Node(item);
        }

        if (subTree.item.compareTo(item) >= 0) {
            subTree.left = add(item, subTree.left);
        } else {
            subTree.right = add(item, subTree.right);
        }

        updateHeight(subTree);

        if (balancing) {
            return rebalance(subTree);   
        }

        return subTree;
    }

    private Node rebalance(Node node) {
        balancing = false;


        //left-left case
        if (node.balanceFactor > 1 && node.left.balanceFactor >= 0){
            return rotateRight(node);
        } 

        //right-right shape
        if (node.balanceFactor < -1 && node.right.balanceFactor <= 0){
            return rotateLeft(node);
        } 

        //left-right shape
        if (node.balanceFactor > 1 && node.left.balanceFactor < 0){
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        //right-left shape
        if (node.balanceFactor < -1 && node.right.balanceFactor > 0){
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        } 


        return node; 
    } 

    private Node rotateLeft(Node n) {
        Node nRight = n.right;
        Node nRightLeft = nRight.left;

        nRight.left = n;
        n.right = nRightLeft;
        
        updateHeight(n);
        updateHeight(nRight);
       

        rotations++;
        return nRight;
    }

    private Node rotateRight(Node n) {
        Node nLeft = n.left;
        Node nLeftRight = nLeft.right;

        nLeft.right = n;
        n.left = nLeftRight;

        updateHeight(n);
        updateHeight(nLeft);
       

        rotations++;
        return nLeft;
    }

    public void remove(T item){
        this.root = remove(item, root);
    }

    private Node remove(T item, Node subTree){
        if (subTree == null) {
            return null;
        }

        if (subTree.item.compareTo(item) == 0) {
            size--;
            if (subTree.right == null && subTree.left == null) {
                return null;
            } else if (subTree.right == null) {
                return subTree.left;
            } else if (subTree.left == null) {
                return subTree.right;
            } else {
              
                if (subTree.right.left != null) {
                    T val = leftMostRightSubTree(subTree.right);
                    subTree.item = val;
                    subTree.right = remove(val, subTree.right);
                    updateHeight(subTree);
                    return subTree;
                } else {
                    T val = rightMostLeftSubTree(subTree.left);
                    subTree.item = val;
                    subTree.left = remove(val, subTree.left);
                    updateHeight(subTree);
                    return subTree;
                }

            }
        } else if (subTree.item.compareTo(item) > 0) {
            subTree.left = remove(item, subTree.left);
        } else {
            subTree.right = remove(item, subTree.right);
        }

        updateHeight(subTree);
        if (balancing) {
            return rebalance(subTree);   
        }

        return subTree;
    }

    private T leftMostRightSubTree(Node n) {
        if (n.left == null) {
            return n.item;
        } else {
            return leftMostRightSubTree(n.left);
        }
    }

    private T rightMostLeftSubTree(Node n) {
        if (n.right == null) {
            return n.item;
        } else {
            return rightMostLeftSubTree(n.right);
        }
    }

    public T find(T item){
        return find(item, root);
    }

    private T find(T item, Node subTree){
        comparisons++;
        if (subTree == null) {
            return null;
        }

        if (subTree.item.compareTo(item) == 0) {
            return subTree.item;
        } else if (subTree.item.compareTo(item) > 0) {
            return find(item, subTree.left);
        } else {
            return find(item, subTree.right);
        }
    }

    public int height() {
        if (root == null) return -1;

        int left = -1;
        int right = -1;

        if (root != null && root.left != null) {
            left = root.left.height;
        }

        if (root != null && root.right != null) {
            right = root.right.height;
        }

        return Math.max(left, right) + 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void updateHeight(Node node) {
        int left = -1;
        int right = -1;

        if (node!= null && node.left != null) {
            left = node.left.height;
        }

        if (node!= null && node.right != null) {
            right = node.right.height;
        }

        node.height = Math.max(left, right) + 1;
        node.balanceFactor = left - right;

        if (Math.abs(node.balanceFactor) >= 2) {
            balancing = true;
        }
    }

    public String toString(){
        if (root == null) {
            return "[]";
        }
        StringBuilder res = toString(root, new StringBuilder("["));
        res.delete(res.length() - 2, res.length());
        res.append("]");

        return res.toString();
    }

    private StringBuilder toString(Node n, StringBuilder s) {
        if (n == null) {
            return s;
        }

        if (n != null && n.left != null) {
            toString(n.left, s);
        }

        s.append(n.toString() + ", ");

        if (n != null && n.right != null) {
            toString(n.right, s);
        }

        return s;
    }

    private class Node {
        public int balanceFactor;
        public T item;
        public Node left;
        public Node right;
        public int height;

        public Node(T item) {
            this.item = item;
            left = null;
            right = null;
            height = 0;
            balanceFactor = 0;
        }

        public String toString() {
            return String.format("%s:H%d:B%d", item, height,balanceFactor);
        }
    }
}
