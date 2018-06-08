package DNSExamMakeover;

import java.io.*;
import java.util.*;

public class Cheaters {
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
            return c == '\n' || c == '\r' || c == '\t' || c == -1;
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

    static class TweakedHashSet<T> extends LinkedHashSet<T> {

        @Override
        public boolean add(T e) {
            // Get rid of old one.
            boolean wasThere = remove(e);
            // Add it.
            super.add(e);
            // Contract is "true if this set did not already contain the specified element"
            return !wasThere;
        }

    }

    static OutputWriter out = new OutputWriter();

    static HashMap<String, HashMap<String, TweakedHashSet<String>>> map = new HashMap<>();
    static HashMap<String, HashSet<String>> done = new HashMap<>();

    public static void main(String[] args) {
        InputReader in = new InputReader();


        int num = in.readInt();
        for (int i = 0; i < num; i++) {
            String[] input = in.readLine().split(" ");

            StringBuilder subjectSB = new StringBuilder();
            for (int j = 2; j < input.length; j++) {
                subjectSB.append(input[j]);
                subjectSB.append(" ");
            }
            String subject = subjectSB.substring(0, subjectSB.length() - 1);

            if (!map.containsKey(subject)) map.put(String.valueOf(subject), new HashMap<>());
            if (!map.get(subject).containsKey(input[0])) map.get(subject).put(input[0], new TweakedHashSet<>());
            map.get(subject).get(input[0]).add(input[1]);
        }

        for (String s : map.keySet()) {
            done.put(s, new HashSet<>());
        }

        int newNum = in.readInt();
        String[][] tasks = new String[num][2];

        for (int i = 0; i < newNum; i++) {
            String input = in.readLine();
            int index = input.indexOf(" ");
            tasks[i][0] = input.substring(0, index);
            tasks[i][1] = input.substring(index + 1);
        }

        for (int i = 0; i < newNum; i++) {
            findDependencies(tasks[i][0], tasks[i][1]);
        }

        out.close();
    }

    private static void findDependencies(String person, String subject) {
        if (done.get(subject).contains(person)) {
            printTHS(subject, person);
            return;
        }

        recurse(subject, person, map.get(subject));
        printTHS(subject, person);
    }

    private static void printTHS(String subject, String person) {
        if (!map.get(subject).containsKey(person)) {
            out.printLine(person);
            return;
        }
        Stack<String> print = new Stack<>();
        print.push(person);

        for (String s : map.get(subject).get(person)) {
            print.push(", ");
            print.push(s);
        }

        while (!print.isEmpty()) {
            out.print(print.pop());
        }
        out.printLine();
    }

    private static TweakedHashSet<String> recurse(String subject, String person, HashMap<String, TweakedHashSet<String>> m) {
        if (!m.containsKey(person)) return new TweakedHashSet<>();
        if (done.get(subject).contains(person)) return m.get(person);
        Queue<String> q = new LinkedList<>(m.get(person));
        while (!q.isEmpty()) {
            m.get(person).addAll(recurse(subject, q.poll(), m));
        }
        done.get(subject).add(person);
        return m.get(person);
    }
}
