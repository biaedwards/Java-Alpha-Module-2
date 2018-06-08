package DNSExam;

import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class SupermarketQueue {
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

    static InputReader in = new InputReader();
    static OutputWriter out = new OutputWriter();

    static LinkedList<String> queue = new LinkedList<>();
    static HashMap<String, Integer> names = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            String input = in.readLine();
            if (input.equals("Append")) {
                append(in.readLine());
            } else if (input.equals("Insert")) {
                insert(in.readInt(), in.readLine());
            } else if (input.equals("Find")) {
                find(in.readLine());
            } else if (input.equals("Serve")) {
                serve(in.readInt());
            } else {
                out.close();
                return;
            }
        }
    }

    private static void append(String name) {
        queue.add(name);
        names.put(name, names.getOrDefault(name, 0)+1);
        out.printLine("OK");
    }

    private static void insert(int position, String name) {
        if(position>queue.size()){
            out.printLine("Error");
            return;
        }
        queue.add(position, name);
        names.put(name, names.getOrDefault(name, 0)+1);
        out.printLine("OK");
    }

    private static void find(String name) {
        out.printLine(names.getOrDefault(name, 0));
    }

    private static void serve(int count) {
        if(count>queue.size()){
            out.printLine("Error");
            return;
        }

        for (int i = 0; i < count; i++) {
            String name = queue.pollFirst();
            names.put(name, names.get(name)-1);
            out.print(name+" ");
        }
        out.printLine();
    }
}
