package LeetCode.HashTables;

import java.util.*;

public class SmallestRange {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        int lists = Integer.parseInt(in.nextLine());
        List<List<Integer>> nums = new ArrayList<>(lists);
        for (int i = 0; i < lists; i++) {
            nums.add(new ArrayList<>());
            var thisList = in.nextLine().split(",");
            for (String aThisList : thisList) {
                nums.get(i).add(Integer.parseInt(aThisList));
            }
        }
        System.out.println(Arrays.toString(new Solution().smallestRange(nums)));
    }

    static class Solution {
        private int[] smallestRange(List<List<Integer>> nums) {
            class Unit implements Comparable {
                int value;
                int index;
                int list;

                Unit(int value, int index, int list) {
                    this.value = value;
                    this.index = index;
                    this.list = list;
                }

                Unit getNext() {
                    int newValue = nums.get(list).get(index + 1);
                    Unit next = new Unit(newValue, index + 1, list);
                    return next;
                }

                boolean hasNext() {
                    if (index + 1 == nums.get(list).size()) return false;
                    else return true;
                }

                int subtract(Unit u) {
                    return this.value - u.value;
                }

                @Override
                public boolean equals(Object o){
                    Unit u = (Unit) o;
                    if(this.value!=u.value) return false;
                    if(this.index!=u.index) return false;
                    if(this.list!=u.list) return false;
                    return true;
                }

                @Override
                public int compareTo(Object o) {
                    Unit u = (Unit) o;
                    if(this.equals(o)) return -1;
                    if (this.value < u.value) return -1;
                    if (this.value > u.value) return 1;
                    if (!this.hasNext()) return -1;
                    if (!u.hasNext()) return 1;
                    return this.getNext().compareTo(u.getNext());
                }
            }

            TreeSet<Unit> current = new TreeSet<>();

            for (int i = 0; i < nums.size(); i++) {
                current.add(new Unit(nums.get(i).get(0), 0, i));
            }

            TreeSet<Unit> best = new TreeSet<>(current);
            int lengthB = best.last().subtract(best.first());

            while (true) {
                Unit min = current.pollFirst();
                assert min != null;
                if (!min.hasNext()) break;
                current.add(min.getNext());
                int lengthC = current.last().subtract(current.first());
                if (lengthC < lengthB) {
                    best = new TreeSet<>(current);
                    lengthB = lengthC;
                }
            }
            return new int[]{best.first().value, best.last().value};
        }
    }
}
