package DNSExam;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class OnlineMarket {
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
            return  c == '\n' || c == '\r' || c == '\t' || c == -1;
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
    static DecimalFormat df = new DecimalFormat("#.########################################");

    static HashSet<String> names = new HashSet<>();
    static HashMap<String, TreeSet<Product>> types = new HashMap<>();
    static TreeSet<Product> products = new TreeSet<>();

    public static void main(String[] args) {
        while (true) {
            String[] input = in.readLine().split(" ");
            if(input[0].equals("add")){
                add(input[1], Double.valueOf(input[2]), input[3]);
            } else if(input[0].equals("end")){
                out.close();
                return;
            } else if(input[2].equals("type")){
                filterByType(input[3]);
            } else if(input[3].equals("to")){
                filterByPriceMax(Double.valueOf(input[4]));
            } else if(input.length<=5){
                filterByPriceMin(Double.valueOf(input[4]));
            } else{
                filterByPriceMinMax(Double.valueOf(input[4]), Double.valueOf(input[6]));
            }
        }
    }

    private static void add(String name, double price, String type) {
        if(names.contains(name)){
            out.printLine("Error: Product "+name+" already exists");
        } else{
            Product temp = new Product(name, price, type);
            names.add(name);
            products.add(temp);
            if(!types.containsKey(type)) types.put(type, new TreeSet<>());
            types.get(type).add(temp);
            out.printLine("Ok: Product "+name+" added successfully");
        }
    }

    private static void filterByType(String type){
        if(!types.containsKey(type)){
            out.printLine("Error: Type "+type+" does not exists");
        } else{
            out.print("Ok: ");
            int counter = 0;
            for(Product p:types.get(type)){
                if(++counter==1) out.print(p);
                else out.print(", " + p);
                if(counter==10) break;
            }
            out.printLine();
        }
    }

    private static void filterByPriceMinMax(double min, double max){
        out.print("Ok: ");
        int counter = 0;
        for (Product p:products){
            if(p.price<min) continue;
            if(p.price>max||counter==10)break;

            if(++counter==1) out.print(p);
            else out.print(", " + p);
        }
        out.printLine();
    }

    private static void filterByPriceMin(double min){
        out.print("Ok: ");
        int counter = 0;
        for (Product p:products){
            if(p.price<=min) continue;
            if(counter==10)break;

            if(++counter==1) out.print(p);
            else out.print(", " + p);
        }
        out.printLine();
    }

    private static void filterByPriceMax(double max){
        out.print("Ok: ");
        int counter = 0;
        for (Product p:products){
            if(p.price>=max||counter==10)break;
            if(++counter==1) out.print(p);
            else out.print(", " + p);
        }
        out.printLine();
    }

    private static class Product implements Comparable {
        String name;
        double price;
        String type;


        public Product(String name, double price, String type) {
            this.name = name;
            this.type = type;
            this.price = price;
        }

        @Override
        public String toString(){
            return name+"("+df.format(price)+")";
        }

        @Override
        public int compareTo(Object o) {
            Product p = (Product) o;
            if (price < p.price) return -1;
            else if (price > p.price) return 1;
            else return this.name.compareToIgnoreCase(p.name);
        }
    }
}
