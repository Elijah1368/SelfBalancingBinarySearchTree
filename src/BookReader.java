import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BookReader {
    public String book;
    public MyLinkedList<String> words;


    public BookReader(String filename) {
        readBook(filename);
        parseWords();
    }

    public void readBook(String filename){
        long start = System.currentTimeMillis();
       
        StringBuilder bookBuilder = new StringBuilder();

        try {
            File f = new File(filename);
            FileReader fr = new FileReader(f);   
            BufferedReader br = new BufferedReader(fr);  
            int c = 0;             
            while((c = br.read()) != -1)        
            {
                bookBuilder.append((char) c);
            }
            br.close();
        } catch (IOException e){
            System.out.println("invalid input: " + e);
        }

        book = bookBuilder.toString();
        long time = System.currentTimeMillis() - start;
        System.out.printf("Reading input file \"%s\"...%d of characters in %d miliseconds %n", filename, book.length(), time);
    }
    
    public void parseWords(){
     
        words = new MyLinkedList<>();
        String temp = book;
        long start = System.currentTimeMillis();
        Pattern p = Pattern.compile("[^a-zA-Z0-9' ]");
        temp = p.matcher(temp).replaceAll(" ");
        Scanner sc = new Scanner(temp).useDelimiter("\\s+");    
        
        while (sc.hasNext()) {
            words.addBefore(sc.next());
        }
        
        sc.close();
        long time = System.currentTimeMillis() - start;

        System.out.printf("Finding words and adding them to a linked list... in %d milliseconds %n", time);
        System.out.printf("The linked list has a length of %d%n%n", words.size());
    }
    
}
