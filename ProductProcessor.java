import java.util.*;
import java.util.stream.*;

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " - " + category + " - ₹" + price;
    }
}

public class ProductProcessor {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("iPhone", "Electronics", 99999),
            new Product("MacBook", "Electronics", 199999),
            new Product("Shirt", "Clothing", 1999),
            new Product("Jeans", "Clothing", 2999),
            new Product("Mixer", "Appliances", 4999),
            new Product("TV", "Electronics", 49999)
        );

        System.out.println("Grouped by Category:");
        Map<String, List<Product>> grouped = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory));
        grouped.forEach((category, productList) -> {
            System.out.println(category + ": " + productList);
        });

        System.out.println("\nMost Expensive Product in Each Category:");
        products.stream()
            .collect(Collectors.groupingBy(Product::getCategory,
                Collectors.collectingAndThen(
                    Collectors.maxBy(Comparator.comparingDouble(Product::getPrice)),
                    Optional::get)))
            .forEach((category, product) -> 
                System.out.println(category + ": " + product));

        System.out.println("\nAverage Price of All Products:");
        double avgPrice = products.stream()
            .mapToDouble(Product::getPrice)
            .average()
            .orElse(0.0);
        System.out.printf("₹%.2f\n", avgPrice);
    }
}
