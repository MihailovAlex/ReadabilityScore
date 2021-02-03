import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Readability {
    public static void main(String[] args) {
        List<String> text = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("C:\\Users\\mihai\\Downloads\\dataset_91069.txt"));
            while (sc.hasNext()) {
                text.add(sc.next());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


        int words = countingWords(text);
        int sentences = countingSentences(text);
        int characters = countingCharacters(text);
        int syllables = countingSyllables(text)[0];
        int polysyllables = countingSyllables(text)[1];
        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);
        showMenu(words, sentences, characters, syllables, polysyllables);

    }

    public static int countingWords(List<String> words) {
        return words.size();
    }

    public static int countingSentences(List<String> words) {
        int countSen = 0;
        for (String word : words) {
            if (word.matches("\\w*[!?.]")) {
                countSen++;
            }
        }
        if (words.get(words.size() - 1).matches("\\w*[^!?.]")) {
            countSen++;
        }
        return countSen;
    }

    public static int countingCharacters(List<String> words) {
        int countChar = 0;
        for (String word : words) {
            countChar += word.length();
        }
        return countChar;
    }

    public static int[] countingSyllables(List<String> words) {
        int countSyll = 0;
        int sumCountSyll = 0;
        int sumCountPolySyll = 0;
        String regexVowel = "[aeiouyAEIOUY]";
        String regexVowel_2 = "[aeiouyAEIOUY]{2,}";
        String regexVowel_3 = "e[!?/.]?\\b";
        for (String word : words) {
            Pattern p = Pattern.compile(regexVowel);
            Matcher m = p.matcher(word);
            while (m.find()) {
                countSyll++;
            }
            Pattern p2 = Pattern.compile(regexVowel_2);
            Matcher m2 = p2.matcher(word);
            Pattern p3 = Pattern.compile(regexVowel_3);
            Matcher m3 = p3.matcher(word);
            while (m2.find()) {
                countSyll--;
                if (Pattern.compile("[aeiouyAEIOUY]{3}").matcher(word).find()) {
                    countSyll--;
                }
            }
            while ((m3.find())) {
                countSyll--;
            }

            if (countSyll == 0) {
                countSyll = 1;
            }
            sumCountSyll += countSyll;

            if (countSyll > 2) {
                sumCountPolySyll++;
            }
            countSyll = 0;
        }
        int[] rez = new int[]{sumCountSyll, sumCountPolySyll};
        return rez;
    }

    public static void showMenu(int words, int sentences, int characters, int syl, int polysyl) {
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.next();
        System.out.println();
        switch (choice) {
            case "ARI":
                countingARI(words, sentences, characters);
                break;
            case "FK":
                countingFK(words, sentences, syl);
                break;
            case "SMOG":
                countingSMOG(sentences, polysyl);
                break;
            case "CL":
                countingCL(sentences, words, characters);
                break;
            case "all":
                countingARI(words, sentences, characters);
                countingFK(words, sentences, syl);
                countingSMOG(sentences, polysyl);
                countingCL(sentences, words, characters);
                countingAverage(words, sentences, characters, syl, polysyl);
                break;
        }
    }

    public static void countingARI(int words, int sentences, int characters) {
        double index = 4.71 * ((double) characters / words) + 0.5 * ((double) words / sentences)
                - 21.43;
        String age = chooseAge(index);
        System.out.println("Automated Readability Index:" + index + " (about " + age + "-year-olds).");
    }

    public static void countingFK(int words, int sentences, int syllables) {
        double index = 0.39 * ((double) words / sentences) + 11.8 * ((double) syllables / words)
                - 15.59;
        String age = chooseAge(index);
        System.out.println("Flesch–Kincaid readability tests:" + index + " (about " + age + "-year-olds).");
    }

    public static void countingSMOG(int sentences, int polysyllables) {
        double index = 1.043 * Math.sqrt((double) polysyllables * 30 / sentences) + 3.1291;
        String age = chooseAge(index);
        System.out.println("Simple Measure of Gobbledygook:" + index + " (about " + age + "-year-olds).");
    }

    public static void countingCL(int sentences, int words, int characters) {
        double index = 0.0588 * (double) characters / words * 100 - 0.296 * (double) sentences / words * 100 - 15.8;
        String age = chooseAge(index);
        System.out.println("Coleman–Liau index:" + index + " (about " + age + "-year-olds).");
    }

    public static void countingAverage(int words, int sentences, int characters, int syllables, int polysyllables) {
        double index1 = 4.71 * ((double) characters / words) + 0.5 * ((double) words / sentences) - 21.43;
        double age1 = Double.parseDouble(chooseAge(index1));
        double index2 = 0.39 * ((double) words / sentences) + 11.8 * ((double) syllables / words) - 15.59;
        double age2 = Double.parseDouble(chooseAge(index2));
        double index3 = 1.043 * Math.sqrt((double) polysyllables * 30 / sentences) + 3.1291;
        double age3 = Double.parseDouble(chooseAge(index3));
        double index4 = 0.0588 * (double) characters / words * 100 - 0.296 * (double) sentences / words * 100 - 15.8;
        double age4 = Double.parseDouble(chooseAge(index4));
        double avg = (age1 + age2 + age3 + age4) / 4;
        System.out.printf("\n%s%.2f%s", "This text should be understood in average by ", avg, "-year-olds.");
    }

    public static String chooseAge(double index) {
        int rez = (int) Math.round(index);
        switch (rez) {
            case 1:
                return "6";
            case 2:
                return "7";
            case 3:
                return "9";
            case 4:
                return "10";
            case 5:
                return "11";
            case 6:
                return "12";
            case 7:
                return "13";
            case 8:
                return "14";
            case 9:
                return "15";
            case 10:
                return "16";
            case 11:
                return "17";
            case 12:
                return "18";
            default:
                return "24";
        }
    }
}
