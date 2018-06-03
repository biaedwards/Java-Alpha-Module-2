//http://judge.telerikacademy.com/problem/05truck
package Judge;

import java.io.*;
import java.util.*;

public class Kamion {
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

    public static void main(String[] args){
        InputReader in = new InputReader();
        OutputWriter out = new OutputWriter();
        int destinations = in.readInt();
        int paths = in.readInt();
        Kruskal kruskal = new Kruskal(destinations);
        for (int i = 0; i < paths; i++) {
            kruskal.addEdge(in.readInt()-1, in.readInt()-1, in.readInt());
        }
        int min = kruskal.kruskal().peekLast()[2];
        out.printLine(min);
        out.close();
    }
    private static class Kruskal {
        private int nodes;
        private PriorityQueue<int[]> edges;

        Kruskal(int nodes) {
            this.nodes = nodes;
            edges = new PriorityQueue<>(Comparator.comparingInt(x -> -x[2]));
        }

        LinkedList<int[]> kruskal() {
            var disjointSet = new int[nodes];
            for (var i = 0; i < nodes; i++) {
                disjointSet[i] = -1;
            }
            LinkedList<int[]> tree = new LinkedList<>();
            while (true) {
                var temp = edges.poll();
                var x = find(temp[0], disjointSet);
                var y = find(temp[1], disjointSet);
                if (x != y) {
                    tree.add(temp);
                    disjointSet[y] += disjointSet[x];
                    if (disjointSet[y] == -1 * nodes) break;
                    disjointSet[x] = y;
                }
            }
            return tree;
        }

        private int find(int node, int[] disjointSet) {
            while (disjointSet[node] > -1) node = disjointSet[node];
            return node;
        }

        void addEdge(int x, int y, int length) {
            int[] edge = {x, y, length};
            edges.offer(edge);
        }
    }
}
