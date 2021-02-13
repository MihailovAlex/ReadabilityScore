import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Count {

    public static int countingWords(List<String> words) {
        return words.size();
    }

    public static int countingSentences(List<String> words) {
        int countSen = 0;
        for (String word : words) {
            if (word.matches("\\w*[!?.]") || word.matches("[Р-пр-џ]*[!?.]")) {
                countSen++;
            }
        }
        if (words.get(words.size() - 1).matches("\\w*[^!?.]")
                || words.get(words.size() - 1).matches("[Р-пр-џ]*[^!?.]")) {
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
}
