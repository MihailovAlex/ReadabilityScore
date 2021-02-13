import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Readability {
    public static void main(String[] args) {
        List<String> text = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\mihai\\Downloads\\dataset_91069.txt");
            FileInputStream fis = new FileInputStream(file);
            Scanner sc = new Scanner(fis);
            while (sc.hasNext()) {
                text.add(sc.next());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(text.toString());
        int words = Count.countingWords(text);
        int sentences = Count.countingSentences(text);
        int characters = Count.countingCharacters(text);
        int syllables = Count.countingSyllables(text)[0];
        int polysyllables = Count.countingSyllables(text)[1];
        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences);
        System.out.println("Characters: " + characters);
//        System.out.println("Syllables: " + syllables);
//        System.out.println("Polysyllables: " + polysyllables);
        Menu.showMenu(words, sentences, characters, syllables, polysyllables);
    }
}
