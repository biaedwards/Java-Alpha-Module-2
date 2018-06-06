package DNSExam;

import java.io.*;
import java.util.*;

public class Packages {
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

    static HashSet<Integer> checked = new HashSet<>();
    static HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

    public static void main(String[] args) {
        InputReader in = new InputReader();
        OutputWriter out = new OutputWriter();
        int n = in.readInt();
        for (int i = 0; i < n; i++) {
            int dependent = in.readInt();
            if(!map.containsKey(dependent)) map.put(dependent, new HashSet<>());
            map.get(dependent).add(in.readInt());
        }
        n = in.readInt();
        int[] packages = new int[n];
        HashSet<Integer> checked = new HashSet<>();
        for (int i = 0; i < n; i++) {
            packages[i] = in.readInt();
        }

        for (int aPackage : packages) {
            TreeSet<Integer> result = new TreeSet<>(recurse(aPackage));
            result.add(aPackage);
            for (Integer x : result) {
                out.print(x);
                out.print(' ');
            }
            out.printLine();
        }

        out.close();
    }

    private static HashSet<Integer> recurse(Integer pack){
        if(!map.containsKey(pack)) return new HashSet<>();
        if(checked.contains(pack)) return map.get(pack);
        Queue<Integer> q= new LinkedList<>(map.get(pack));
        while(!q.isEmpty()){
            map.get(pack).addAll(recurse(q.poll()));
        }
        checked.add(pack);
        return map.get(pack);
    }
}

