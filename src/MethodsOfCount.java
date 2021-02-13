public class MethodsOfCount {

    static String format = "\n%s %.2f%s%s%s";

    public static void countingARI(int words, int sentences, int characters) {
        double index = 4.71 * ((double) characters / words) + 0.5 * ((double) words / sentences)
                - 21.43;
        String age = chooseAge(index);
        System.out.printf(format, "Automated Readability Index:", index, " (about ", age, "-year-olds).");
    }

    public static void countingFK(int words, int sentences, int syllables) {
        double index = 0.39 * ((double) words / sentences) + 11.8 * ((double) syllables / words)
                - 15.59;
        String age = chooseAge(index);
        System.out.printf(format, "Flesch–Kincaid readability tests:", index, " (about ", age, "-year-olds).");
    }

    public static void countingSMOG(int sentences, int polysyllables) {
        double index = 1.043 * Math.sqrt((double) polysyllables * 30 / sentences) + 3.1291;
        String age = chooseAge(index);
        System.out.printf(format, "Simple Measure of Gobbledygook:", index, " (about ", age, "-year-olds).");
    }

    public static void countingCL(int sentences, int words, int characters) {
        double index = 0.0588 * (double) characters / words * 100 - 0.296 * (double) sentences / words * 100 - 15.8;
        String age = chooseAge(index);
        System.out.printf(format, "Coleman–Liau index:", index, " (about ", age, "-year-olds).");
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
        System.out.printf("\n\n%s%.0f%s", "This text should be understood in average by ", avg, "-year-olds.");
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
