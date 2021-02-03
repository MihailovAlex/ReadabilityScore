import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GJf {
    public static void main(String[] args) {
        String regexVowel_3 = "[aeiouyAEIOUY]{2,}";
        Scanner sc = new Scanner(System.in);
        String enter = sc.nextLine();
        Pattern p3 = Pattern.compile(regexVowel_3);
        Matcher m3 = p3.matcher(enter);
        int countSyll = 0;

        System.out.println(countSyll);
    }


}
