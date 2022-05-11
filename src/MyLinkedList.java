
public class MyLinkedList<T extends Comparable<T>> {
    public long comparisons;
    private Node<T> first;
    private Node<T> current;
    private Node<T> previous;
    private int size;

    public MyLinkedList(T elem) {
        var newNode = new Node<T>(elem);
        first = newNode;
        current = newNode;
        previous = null;
        size = 1;
        comparisons  =0;
    }

    public MyLinkedList() {
        first = null;
        current = null;
        previous = null;
        size = 0;
        comparisons = 0;
    }

    public void addBefore(T elem) {

        var newNode = new Node<T>(elem);
        //if first element in list
        if (current == null && size == 0) {
            previous = newNode;
            first = newNode;
        //if current is at last position of list
        } else if (current == null) {
            previous.next = newNode;
            previous = newNode;
        //if current is first
        } else if (previous == null) {
            var placeholder = first;
            first = newNode;
            newNode.next = placeholder;
        } else {
            previous.next = newNode;
            newNode.next = current;
            previous = newNode;
        }

        size +=1;
    }
    
    public void addAfter(T elem) {
        var newNode = new Node<T>(elem);
        if (current == null) { 
            return;
        } else {
            var placeholder = current.next;
            current.next = newNode;
            newNode.next = placeholder;
        }
        size +=1;
    }
    
    //bruh
    public T remove() {
        T objRemoved = null;
        
        //if current is null
        if (current == null) {
            return objRemoved;
        } 
        
        objRemoved = current.item;
        //if first element of list
        if (previous == null){
            var placeholder = current.next;
            current.next = null;
            current = placeholder;
            first = placeholder;
        //if middle of the list
        } else {
            var placeholder = current.next;
            current.next = null;
            current = placeholder;
            previous.next = placeholder;
        }

        size -= 1;

        return objRemoved;
    }

    public T current(){
        return current != null ? current.item : null;
    }

    public T first() {
        if (first == null) {
            return null;
        }

        current = first;
        previous = null;

        return first.item;
    }

    public T next() {
        if (current == null) {
            return null;
        }

        previous = current;
        current = current.next;


        return current == null ? null : current.item;
    }

    public boolean contains(T elem) {
        comparisons += 1;

        if (size <= 0) {
            return false;
        }
        
        var temp = first;
        
        while (temp != null) {
            comparisons += 1;
            if (temp.item.compareTo(elem) == 0) {
                return true;
            }
            
            temp = temp.next;
        }

        return false;
    }

    public int indexOf(T elem) {
        if (size <= 0) {
            return -1;
        }
        
        var temp = first;

        int index = 0;
        while (temp != null) {
            if (temp.item.compareTo(elem) == 0) {
                return index;
            }
            temp = temp.next;
            index++;
        }

        return -1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public String toString(){
        if (size == 0) return "[]";

        StringBuilder str = new StringBuilder("[");
        var temp = first;
        str.append(temp.item);
        temp = temp.next;

        while (temp != null) {
            str.append(", " + temp.item);
            temp = temp.next;
        }

        str.append("]");
        return str.toString();
    }

    private void swap(Node<T> a, Node<T> b) {
        if (a == b) return;
        T temp = a.item;
        a.item = b.item;
        b.item = temp;
    }

    //quicksort
    public void sort() {
        if (size <= 1) return;
        
        //gets last node
        var last = first;
        while (last != null && last.next != null) {
            last = last.next;
        }

        quickSort(first, last);
        
    }

    private void quickSort(Node<T> start, Node<T> end){
        if (start == end || start == null || end == null) {
            return;
        } else {
            var pivot = start;
            var curr = start;

            while(curr != end.next) {
                if (curr.item.compareTo(start.item) < 0) {
                    pivot = pivot.next;
                    swap(pivot, curr);
                } 
                curr = curr.next;
            }
            swap(start, pivot);
            quickSort(start, pivot);
            quickSort(pivot.next, end);
        }
    }
    

}

class Node<T>  {
    public T item;
    public Node<T> next;
    
    public Node(){
        this.item = null;
        this.next = null;
    }

    public Node(T item){
        this.item = item;
        this.next = null;
    }

    public Node(T item, Node<T> next){
        this.item = item;
        this.next = next;
    }

    public String toString(){
        return this.item.toString();
    }
}