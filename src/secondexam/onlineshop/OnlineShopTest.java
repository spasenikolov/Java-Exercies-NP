package secondexam.onlineshop;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

enum COMPARATOR_TYPE {
    NEWEST_FIRST,
    OLDEST_FIRST,
    LOWEST_PRICE_FIRST,
    HIGHEST_PRICE_FIRST,
    MOST_SOLD_FIRST,
    LEAST_SOLD_FIRST
}

class ProductNotFoundException extends Exception {
    ProductNotFoundException(String message) {
        super(message);
    }
}


class Product {

    //String category, String id, String name, LocalDateTime createdAt, double price) - м
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private double price;
    private int numSold;
    private String category;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", numSold=" + numSold +
                '}';
    }

    public Product(String category, String id, String name, LocalDateTime createdAt, double price) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.price = price;
        this.numSold = 0;
    }

    public String getCategory() {
        return category;
    }

    public void increaseNumSoldBy (int howMuch){
        this.numSold+=howMuch;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public double getPrice() {
        return price;
    }

    public int getNumSold() {
        return numSold;
    }
}


class OnlineShop {
    List<Product> products;
    Map<String, Product> productsById;
    Map<String, List<Product>> productsByCategory;


    OnlineShop() {
        this.products = new ArrayList<>();
        this.productsById = new HashMap<>();
        this.productsByCategory = new HashMap<>();
    }

    void addProduct(String category, String id, String name, LocalDateTime createdAt, double price) {
        Product product = new Product(category, id, name, createdAt, price);
        this.products.add(product);

        this.productsById.putIfAbsent(id, product);

        this.productsByCategory.putIfAbsent(category, new ArrayList<>());
        this.productsByCategory.get(category).add(product);
    }

    double buyProduct(String id, int quantity) throws ProductNotFoundException {
        if (!this.productsById.containsKey(id)) {
            throw new ProductNotFoundException("Exception");
        }
        productsById.get(id).increaseNumSoldBy(quantity);
        return productsById.get(id).getPrice() * quantity;
        //return 0.0;
    }

    List<List<Product>> listProducts(String category, COMPARATOR_TYPE comparatorType, int pageSize) {

        List<Product> listToPaginate = productsByCategory.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(product -> {
                    if(category!=null)
                        return product.getCategory().equals(category);
                    else return true;
                })
                .sorted(makeComparator(comparatorType))
                .collect(Collectors.toList());

        List<List<Product>> result = new ArrayList<>();
        for (int i = 0; i < listToPaginate.size(); i+=pageSize){
            result.add(listToPaginate.subList(i, Math.min(i+pageSize,listToPaginate.size())));
        }

        return result;
    }

    private Comparator<Product> makeComparator(COMPARATOR_TYPE comparatorType) {
        Comparator<Product> oldestFirst  = Comparator.comparing(Product::getCreatedAt);
        Comparator<Product> lowestPriceFirst = Comparator.comparing(Product::getPrice);
        Comparator<Product> leastSoldFirst = Comparator.comparing(Product::getNumSold);

        Comparator<Product> theComparator;

        switch (comparatorType){
            case HIGHEST_PRICE_FIRST -> theComparator = lowestPriceFirst.reversed();
            case LOWEST_PRICE_FIRST -> theComparator = lowestPriceFirst;
            case LEAST_SOLD_FIRST -> theComparator = leastSoldFirst.thenComparing(Product::getName);
            case MOST_SOLD_FIRST -> theComparator = leastSoldFirst.reversed().thenComparing(Product::getName);
            case NEWEST_FIRST -> theComparator = oldestFirst.reversed().thenComparing(Product::getName);
            case OLDEST_FIRST -> theComparator = oldestFirst;
            default -> throw new IllegalStateException("Unexpected value: " + comparatorType);
        }
        return theComparator;

    }

}

public class OnlineShopTest {

    public static void main(String[] args) {
        OnlineShop onlineShop = new OnlineShop();
        double totalAmount = 0.0;
        Scanner sc = new Scanner(System.in);
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] parts = line.split("\\s+");
            if (parts[0].equalsIgnoreCase("addproduct")) {
                String category = parts[1];
                String id = parts[2];
                String name = parts[3];
                LocalDateTime createdAt = LocalDateTime.parse(parts[4]);
                double price = Double.parseDouble(parts[5]);
                onlineShop.addProduct(category, id, name, createdAt, price);
            } else if (parts[0].equalsIgnoreCase("buyproduct")) {
                String id = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                try {
                    totalAmount += onlineShop.buyProduct(id, quantity);
                } catch (ProductNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                String category = parts[1];
                if (category.equalsIgnoreCase("null"))
                    category = null;
                String comparatorString = parts[2];
                int pageSize = Integer.parseInt(parts[3]);
                COMPARATOR_TYPE comparatorType = COMPARATOR_TYPE.valueOf(comparatorString);
                printPages(onlineShop.listProducts(category, comparatorType, pageSize));
            }
        }
        System.out.println("Total revenue of the online shop is: " + totalAmount);

    }

    private static void printPages(List<List<Product>> listProducts) {
        for (int i = 0; i < listProducts.size(); i++) {
            System.out.println("PAGE " + (i + 1));
            listProducts.get(i).forEach(System.out::println);
        }
    }
}


