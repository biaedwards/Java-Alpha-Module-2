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

    static OutputWriter out = new OutputWriter();

    static HashMap<String, HashMap<String, TreeSet<String>>> map = new HashMap<>();
    static HashMap<String, HashSet<LinkedHashSet<String>>> found = new HashMap<>();


    public static void main(String[] args) {
        InputReader in = new InputReader();

        int num = in.readInt();
        for (int i = 0; i < num; i++) {
            String input = in.readLine();
            int index1 = input.indexOf(" ");
            int index2 = input.substring(index1+1).indexOf(" ")+index1+1;

            String subject = input.substring(index2+1);
            String person = input.substring(0, index1);

            if (!map.containsKey(subject)) map.put(subject, new HashMap<>());
            if (!map.get(subject).containsKey(person)) map.get(subject).put(person, new TreeSet<>());
            map.get(subject).get(person).add(input.substring(index1+1, index2));
        }

        num = in.readInt();
        String[][] tasks = new String[num][2];

        for (int i = 0; i < num; i++) {
            String input = in.readLine();
            int index = input.indexOf(" ");
            tasks[i][0] = input.substring(0, index);
            tasks[i][1] = input.substring(index + 1);
        }

        for (int i = 0; i < num; i++) {
            if(!isFound(tasks[i][0], tasks[i][1])) findDependencies(tasks[i][0], tasks[i][1]);
        }

        out.close();
    }

    private static void findDependencies(String person, String subject) {
        LinkedHashSet<String> order = new LinkedHashSet<>();
        fillSet(person, map.get(subject), order);
        order.add(person);

        if(!found.containsKey(subject)) found.put(subject, new HashSet<LinkedHashSet<String>>());
        found.get(subject).add(order);

        for(String name:order){
            if(name==person) out.printLine(person);
            else out.print(name+", ");
        }
    }

    private static boolean isFound(String person, String subject){
        if(!found.containsKey(subject)) return false;

        for(LinkedHashSet<String> hs:found.get(subject)){
            if(hs.contains(person)){
                for (String name:hs){
                    if(!name.equals(person)) out.print(name+", ");
                    else{
                        out.printLine(person);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static void fillSet(String person, HashMap<String, TreeSet<String>> m, LinkedHashSet<String> order){
        if(!m.containsKey(person)){
            return;
        }

        for(String s:m.get(person)){
            fillSet(s, m, order);
        }

        order.addAll(m.get(person));
    }
}
