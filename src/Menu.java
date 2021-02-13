import java.util.Scanner;

public class Menu {

    public static void showMenu(int words, int sentences, int characters, int syl, int polysyl) {
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.next();
        System.out.println();
        switch (choice) {
            case "ARI":
                MethodsOfCount.countingARI(words, sentences, characters);
                break;
            case "FK":
                MethodsOfCount.countingFK(words, sentences, syl);
                break;
            case "SMOG":
                MethodsOfCount.countingSMOG(sentences, polysyl);
                break;
            case "CL":
                MethodsOfCount.countingCL(sentences, words, characters);
                break;
            case "all":
                MethodsOfCount.countingARI(words, sentences, characters);
                MethodsOfCount.countingFK(words, sentences, syl);
                MethodsOfCount.countingSMOG(sentences, polysyl);
                MethodsOfCount.countingCL(sentences, words, characters);
                MethodsOfCount.countingAverage(words, sentences, characters, syl, polysyl);
                break;
        }
    }
}
