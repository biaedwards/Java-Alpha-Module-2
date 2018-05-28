package Judge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Cooking {
    public static final Map<String, Unit> measurements = Map.ofEntries(Map.entry("tbsps", new Unit(15)), Map.entry("tablespoons", new Unit(15)),
            Map.entry("tsps", new Unit(5)), Map.entry("teaspoons", new Unit(5)), Map.entry("mls", new Unit(1)),
            Map.entry("milliliters", new Unit(1)), Map.entry("ls", new Unit(1000)), Map.entry("liters", new Unit(1000)),
            Map.entry("cups", new Unit(240)), Map.entry("pts", new Unit(480)), Map.entry("pints", new Unit(480)),
            Map.entry("qts", new Unit(960)), Map.entry("quarts", new Unit(960)), Map.entry("gals", new Unit(3840)),
            Map.entry("gallons", new Unit(3840)), Map.entry("fl ozs", new Unit(30)), Map.entry("fluid ounces", new Unit(30)));
    ;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int originalRecipeProducts = Integer.parseInt(in.readLine());
        Recipe originalRecipe = new Recipe();
        for (int i = 0; i < originalRecipeProducts; i++) {
            originalRecipe.updateProduct(new Product(in.readLine()),1);
        }
        int cookedProducts = Integer.parseInt(in.readLine());
        for (int i = 0; i < cookedProducts; i++) {
            originalRecipe.updateProduct(new Product(in.readLine()), -1);
        }
        originalRecipe.printRemainingProducts();
    }

    public static class Product {
        private String name;
        private String unitName;
        private Unit unit;
        private Double quantity;
        private static final DecimalFormat df = new DecimalFormat("0.00");

        public Product(String input) {
            String quantityStr = input.substring(0, input.indexOf(':'));
            if(quantityStr.length()==0) this.quantity = 0d;
            else this.quantity = Double.valueOf(quantityStr);
            this.unitName = input.substring(input.indexOf(':') + 1, input.indexOf("s:") + 1);
            this.unit = measurements.get(unitName);
            if(input.indexOf("s:") + 2<input.length()) this.name = input.substring(input.indexOf("s:") + 2);
            else this.name = null;
        }

        public String getName() {
            return name;
        }

        public Unit getUnit() {
            return unit;
        }

        public Double getQuantity() {
            return quantity;
        }

        public void changeQuantity(Product product, int multiplier) {
            if(product.getUnit().equals(unit)){
                quantity += multiplier*product.getQuantity()+0.00001;
            }
            else quantity = (quantity * unit.getValue() + (product.getQuantity() * product.getUnit().getValue()) * multiplier) / unit.getValue()+0.00001;
            if (quantity < 0) quantity = 0d;
        }

        public boolean equals(Product product) {
            if(product.getName()==null&&name==null) return true;
            if(product.getName()==null||name==null) return false;
            if (product.getName().equalsIgnoreCase(name)) return true;
            else return false;
        }

        public void print() {
            if(name==null) System.out.printf("%s:%s:\n", df.format(quantity), unitName);
            else System.out.printf("%s:%s:%s\n", df.format(quantity), unitName, name);
        }
    }

    public static class Unit {
        private int value;

        Unit(int valueInMl) {
            this.value = valueInMl;
        }

        public int getValue() {
            return value;
        }

        public boolean equals(Unit unit){
            if(unit.getValue()==value) return true;
            else return false;
        }
    }

    public static class Recipe {
        private HashMap<String, Product> products;

        Recipe() {
            products = new HashMap<>();
        }

        void addProduct(Product product) {
            products.put(product.getName().toLowerCase(),product);
        }

        void updateProduct(Product product, int multiplier) {
           if(!products.containsKey(product.getName().toLowerCase())&&multiplier>0) {
               addProduct(product);
               return;
           }
           products.get(product.getName().toLowerCase()).changeQuantity(product,multiplier);
        }

        void printRemainingProducts() {
            products.keySet().forEach(x -> products.get(x).print());
        }
    }
}
