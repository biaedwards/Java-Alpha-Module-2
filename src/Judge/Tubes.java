package Judge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tubes {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int numberOfTubes = in.nextInt();
        long target = in.nextInt();
        ArrayList<Long> tubes = new ArrayList<>();
        long max = 0L;
        for (int i = 0; i < numberOfTubes; i++) {
            long tube = in.nextInt();
            if(tube>max) max = tube;
            tubes.add(tube);
        }
        System.out.println(findLongest(tubes, target, max));
    }
    private static long findLongest(List<Long> tubes, long target, long max){
        long start = 0;
        long end = max;
        while(start<end){
            int count = 0;
            for(long tube:tubes){
                count+=tube/end;
            }
            if(count<target){
                end = (start+end)/2;
            } else{
                start = end;
                end*=1.5;
            }
        }
        return end;
    }
}
