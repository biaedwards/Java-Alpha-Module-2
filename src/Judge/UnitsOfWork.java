package Judge;

import java.util.*;

public class UnitsOfWork {
    static HashMap<String, Unit> units = new HashMap<>();
    static final Comparator<Unit> cmp = Comparator.comparingInt(Unit::getAttack).reversed().thenComparing(Unit::getName);
    static PriorityQueue<Unit> pq = new PriorityQueue<>(cmp);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(parse(in.nextLine().split(" ")));
    }

    static void addUnit(String name, String type, int attack) {
        if (units.containsKey(name)) {
            System.out.printf("FAIL: %s already exists!\n", name);
        } else {
            Unit temp = new Unit(name, type, attack);
            units.put(name, temp);
            pq.add(temp);
            System.out.printf("SUCCESS: %s added!\n", name);
        }
    }

    static void removeUnit(String name) {
        if (!units.containsKey(name)) {
            System.out.printf("FAIL: %s could not be found!\n", name);
        } else {
            units.remove(name);
            for(Unit unit:pq){
                if(unit.name.equals(name)){
                    pq.remove(unit);
                    break;
                }
            }
            System.out.printf("SUCCESS: %s removed!\n", name);
        }
    }

    static void findType(String type){
        System.out.print("RESULT: ");
        StringBuilder out = new StringBuilder();
        PriorityQueue<Unit> pqCopy = new PriorityQueue<>(pq);
        while(!pqCopy.isEmpty()){
            Unit temp = pqCopy.poll();
            if(!temp.type.equals(type)) continue;
            out.append(String.format("%s, ", temp));
        }
        if(out.length()==0) return;
        out.delete(out.length()-2, out.length());
        System.out.println(out);
    }

    static void power(int number){
        System.out.print("RESULT: ");
        StringBuilder out = new StringBuilder();
        PriorityQueue<Unit> pqCopy = new PriorityQueue<>(pq);
        while(number>0&&!pqCopy.isEmpty()){
            out.append(String.format("%s, ", pqCopy.poll()));
            --number;
        }
        out.delete(out.length()-2, out.length());
        System.out.println(out);
    }

    static boolean parse(String[] input) {
        switch (input[0]) {
            case "add":
                addUnit(input[1], input[2], Integer.parseInt(input[3]));
                break;
            case "remove":
                removeUnit(input[1]);
                break;
            case "find": findType(input[1]);
                break;
            case "power": power(Integer.parseInt(input[1]));
                break;
            case "end": return false;
        }
        return true;
    }

    static class Unit {
        private String name;
        private String type;
        private int attack;

        Unit(String name, String type, int attack) {
            this.name = name;
            this.type = type;
            this.attack = attack;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public int getAttack() {
            return attack;
        }

        @Override
        public String toString(){
            return String.format("%s[%s](%d)", name, type, attack);
        }
    }
}
