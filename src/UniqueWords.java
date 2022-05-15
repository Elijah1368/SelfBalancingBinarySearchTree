
public class UniqueWords {

    private BookReader book;

    public UniqueWords() {
        book = new BookReader("WarAndPeace.txt");
        addUniqueWordsToAVL();

    }

    public void addUniqueWordsToAVL() {
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
        System.out.printf("Adding unique words to an AVL binary search tree.. in %d milliseconds%n", time);
        System.out.printf("%d unique words%n", binarySearchTree.size());
        System.out.printf("%d height%n%d comparisons.%n%d rotations%n", binarySearchTree.height(), binarySearchTree.comparisons, binarySearchTree.rotations);

        //Times sorting unique words
        start = System.currentTimeMillis();
        binarySearchTree.toString();
        time = System.currentTimeMillis() - start;

        System.out.printf("Traversing the AVL... in %d milliseconds%n%n", time);
        //System.out.println(linkedListUnique);
    }
}
