public class MyBinarySearchTree<T extends Comparable<T>>{
    private Node root;
    private int size;
    public long comparisons;

    public MyBinarySearchTree() {
        root = null;
        size = 0;
        comparisons = 0;
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

        return subTree;
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
            if (subTree.height == 0) {
                return null;
            } else if (subTree.right == null) {
                return subTree.left;
            } else if (subTree.left == null) {
                return subTree.right;
            } else {
                T val = leftMostRightSubTree(subTree.right);
                subTree.item = val;
                subTree.right = remove(val, subTree.right);
                return subTree;
            }
        } else if (subTree.item.compareTo(item) > 0) {
            subTree.left = remove(item, subTree.left);
        } else {
            subTree.right = remove(item, subTree.right);
        }

        updateHeight(subTree);

        return subTree;
    }

    private T leftMostRightSubTree(Node n) {
        if (n.left == null) {
            return n.item;
        } else {
            return leftMostRightSubTree(n.left);
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
        public T item;
        public Node left;
        public Node right;
        public int height;

        public Node(T item) {
            this.item = item;
            left = null;
            right = null;
            height = 0;
        }

        public String toString() {
            return String.format("%s:H%d", item, height);
        }
    }
}
