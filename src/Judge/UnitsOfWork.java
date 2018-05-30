package Judge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UnitsOfWork {
    private static HashMap<String, Unit> units = new HashMap<>();
    private static HashMap<String, HashSet<Unit>> types = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (parse(in.readLine().split(" "))) ;
    }

    private static void addUnit(String name, String type, int attack) {
        if (units.containsKey(name)) {
            System.out.printf("FAIL: %s already exists!\n", name);
        } else {
            Unit temp = new Unit(name, type, attack);
            units.put(name, temp);
            if (types.containsKey(type)) {
                types.get(type).add(temp);
            } else {
                HashSet<Unit> hs = new HashSet<>();
                hs.add(temp);
                types.put(type, hs);
            }
            System.out.printf("SUCCESS: %s added!\n", name);
        }
    }

    private static void removeUnit(String name) {
        if (!units.containsKey(name)) {
            System.out.printf("FAIL: %s could not be found!\n", name);
        } else {
            Unit temp = units.get(name);
            units.remove(name);
            types.get(temp.type).remove(temp);
            System.out.printf("SUCCESS: %s removed!\n", name);
        }
    }

    private static void findType(String type) {
        System.out.print("RESULT: ");
        StringBuilder sb = new StringBuilder();
        if(!types.containsKey(type)){
            System.out.println();
            return;
        }
        types.get(type).stream()
                .sorted()
                .forEach(x -> sb.append(x).append(", "));
        if (sb.length() != 0) {
            sb.delete(sb.length() - 2, sb.length());
            System.out.println(sb);
        } else System.out.println();
    }

    private static void power(int number) {
        System.out.print("RESULT: ");
        if(units.isEmpty()){
            System.out.println();
            return;
        }
        StringBuilder sb = new StringBuilder();
        units.values().stream()
                .sorted()
                .limit(number)
                .forEach(x -> sb.append(x).append(", "));
        if (sb.length() != 0) {
            sb.delete(sb.length() - 2, sb.length());
            System.out.println(sb);
        } else System.out.println();
    }

    private static boolean parse(String[] input) {
        switch (input[0]) {
            case "add":
                addUnit(input[1], input[2], Integer.parseInt(input[3]));
                break;
            case "remove":
                removeUnit(input[1]);
                break;
            case "find":
                findType(input[1]);
                break;
            case "power":
                power(Integer.parseInt(input[1]));
                break;
            case "end":
                return false;
        }
        return true;
    }

    static class Unit implements Comparable{
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
        public String toString() {
            return String.format("%s[%s](%d)", name, type, attack);
        }

        @Override
        public int compareTo(Object o) {
            Unit unit = (Unit) o;
            if(Integer.compare(this.attack, unit.attack)!=0) return -Integer.compare(this.attack, unit.attack);
            return this.name.compareTo(unit.name);
        }
    }
}