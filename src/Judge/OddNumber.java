package Judge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class OddNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Long> nums = new HashSet<>();
        long count = Long.parseLong(in.readLine());
        while(count>0){
            long num = Long.parseLong(in.readLine());
            if(nums.contains(num)) nums.remove(num);
            else nums.add(num);
            count--;
        }
        nums.forEach(System.out::println);
    }
}
