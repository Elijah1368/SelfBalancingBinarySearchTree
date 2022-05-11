
public class UniqueWords {

    private BookReader book;

    public UniqueWords() {
        book = new BookReader("WarAndPeace.txt");
        addUniqueWordsToBST();

    }

    public void addUniqueWordsToBST() {
        //Resets pointer to first word
        book.words.first();
        MyBinarySearchTree<String> binarySearchTree = new MyBinarySearchTree<String>();

        //Times adding unique words
        long start = System.currentTimeMillis();

        String currWord = book.words.current();

        while (currWord != null) {
            if (binarySearchTree.find(currWord) == null) {
                binarySearchTree.add(currWord);
            }
            currWord = book.words.next();
        }

        long time = System.currentTimeMillis() - start;
        System.out.printf("Adding unique words to a binary search tree.. in %d milliseconds%n", time);
        System.out.printf("%d unique words%n", binarySearchTree.size());
        System.out.printf("The binary search tree had a height of %d and made %d comparisons.%n", binarySearchTree.height(), binarySearchTree.comparisons);

        //Times sorting unique words
        start = System.currentTimeMillis();
        binarySearchTree.toString();
        time = System.currentTimeMillis() - start;

        System.out.printf("Traversing the binary search tree... in %d milliseconds%n%n", time);
        //System.out.println(linkedListUnique);
    }
}
