import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentWarehouseApp {
    public static void main(String[] args) {
        System.out.println("=== Thread-Safe Concurrent Warehouse Stock Engine ===\n");

        Map<String, StockProduct> productCatalog = new LinkedHashMap<>();
        productCatalog.put("SKU-101", new StockProduct("SKU-101", "NVMe SSD 1TB", 95.00));
        productCatalog.put("SKU-102", new StockProduct("SKU-102", "DDR5 RAM 32GB", 120.00));
        productCatalog.put("SKU-103", new StockProduct("SKU-103", "Mechanical Keyboard", 85.50));
        productCatalog.put("SKU-104", new StockProduct("SKU-104", "USB-C Docking Hub", 64.00));

        ConcurrentHashMap<String, Integer> stockLedger = new ConcurrentHashMap<>();
        int initialStock = 100;
        for (String sku : productCatalog.keySet()) {
            stockLedger.put(sku, initialStock);
        }

        System.out.println("Initialized " + stockLedger.size() + " SKUs with " + initialStock + " starting units each.");
    }
}