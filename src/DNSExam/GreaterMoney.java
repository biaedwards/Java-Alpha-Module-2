package DNSExam;

import java.util.HashMap;
import java.util.Scanner;

public class GreaterMoney {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        HashMap<Integer, Integer> map = new HashMap<>();
        String[] bag1String = in.nextLine().split(",");
        int[] bag1 = new int[bag1String.length];
        for (int i = 0; i < bag1.length; i++) {
            bag1[i] = Integer.parseInt(bag1String[i]);
            map.put(bag1[i], -1);
        }

        String[] bag2String = in.nextLine().split(",");
        int[] bag2 = new int[bag2String.length];
        for (int i = 0; i < bag2.length; i++) {
            bag2[i] = Integer.parseInt(bag2String[i]);
            if(map.containsKey(bag2[i])){
                map.put(bag2[i], i);
            }
        }

        int[] result = new int[bag1.length];
        for (int i = 0; i < bag1.length; i++) {
            int index = map.get(bag1[i]);
            for (int j = index+1; j < bag2.length; j++) {
                if(bag2[j]>bag1[i]){
                    result[i] = bag2[j]+1;
                    break;
                }
            }
            result[i]--;
        }

        for (int i = 0; i < result.length-1; i++) {
            System.out.printf("%d,", result[i]);
        }
        System.out.println(result[result.length-1]);
    }
}
