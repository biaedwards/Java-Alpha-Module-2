package DNSExamMakeover;

import java.util.Scanner;

public class Subsequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String first = in.nextLine().toUpperCase();
        String second = in.nextLine().toUpperCase();

        System.out.println(isSubsequence(first, second) ? "true" : "false");
    }

    private static boolean isSubsequence(String first, String second) {
        int f = 0;
        int s = 0;

        if (first.length() > second.length()) return false;
        while (f < first.length()) {
            while (s < second.length() && second.charAt(s) != first.charAt(f)) s++;
            if (s < second.length()) {
                f++;
                s++;
            } else {
                break;
            }
        }
        if (f == first.length()) return true;
        else return false;
    }
}

