package Judge;

import java.io.*;
import java.util.*;

public class UnitsOfWork {
    static class InputReader {

        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        InputReader() {
            this.stream = System.in;
        }

        int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        long readLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        double readDouble() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.' && c != ',') {
                if (c == 'e' || c == 'E') {
                    return res * Math.pow(10, readInt());
                }
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.' || c == ',') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E') {
                        return res * Math.pow(10, readInt());
                    }
                    if (c < '0' || c > '9') {
                        throw new InputMismatchException();
                    }
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        String readLine() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
    static class OutputWriter {
        private final PrintWriter writer;

        OutputWriter() {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        }

        void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }

        void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        void close() {
            writer.close();
        }
    }

    private static HashMap<String, Unit> units = new HashMap<>();
    private static HashMap<String, TreeSet<Unit>> types = new HashMap<>();
    private static TreeSet<Unit> allOrdered = new TreeSet<>();
    private static InputReader in = new InputReader();
    private static OutputWriter write = new OutputWriter();

    public static void main(String[] args) throws IOException {
        while (true) {
            String input = in.readLine();
            if (input.equals("add")) {
                addUnit(in.readLine(), in.readLine(), in.readInt());
            } else if (input.equals("remove")) {
                removeUnit(in.readLine());
            } else if (input.equals("find")) {
                findType(in.readLine());
            } else if (input.equals("power")) {
                power(in.readInt());
            } else if (input.equals("end")) {
                write.close();
                return;
            }
        }
    }

    private static void addUnit(String name, String type, int attack) {
        if (units.containsKey(name)) {
            write.printLine("FAIL: " + name + " already exists!");
        } else {
            Unit temp = new Unit(name, type, attack);
            units.put(name, temp);
            allOrdered.add(temp);
            if (!types.containsKey(type)) {
                types.put(type, new TreeSet<>());
            }
            types.get(type).add(temp);
            write.printLine("SUCCESS: " + name + " added!");
        }
    }

    private static void removeUnit(String name) {
        if (!units.containsKey(name)) {
            write.printLine("FAIL: " + name + " could not be found!");
        } else {
            Unit temp = units.get(name);
            allOrdered.remove(temp);
            types.get(temp.type).remove(temp);
            units.remove(name);
            write.printLine("SUCCESS: " + name + " removed!");
        }
    }

    private static void findType(String type) {
        write.print("RESULT: ");
        if (!types.containsKey(type)) {
            write.printLine();
            return;
        }
        int counter = 0;
        for (Unit unit : types.get(type)) {
            if(++counter==1) write.print(unit);
            else write.print(", " + unit);
        }
        write.printLine();
    }

    private static void power(int number) {
        write.print("RESULT: ");
        if (allOrdered.isEmpty()) {
            write.printLine();
            return;
        }
        if (number > allOrdered.size()) number = allOrdered.size();
        ++number;
        int counter = 0;
        for (Unit unit : allOrdered) {
            if (++counter==number) break;
            if(counter==1) write.print(unit);
            else write.print(", " + unit);
        }
        write.printLine();
    }

    static class Unit implements Comparable {
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
            if (Integer.compare(this.attack, unit.attack) != 0) return -Integer.compare(this.attack, unit.attack);
            return this.name.compareTo(unit.name);
        }
    }
}
