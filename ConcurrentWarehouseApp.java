import java.util.ArrayList;
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

        List<String> skuList = new ArrayList<>(productCatalog.keySet());
        int workerCount = 4;
        int cyclesPerWorker = 250;
        int unitsPerCycle = 2;

        long startTime = System.nanoTime();

        Thread[] workers = new Thread[workerCount];
        for (int i = 0; i < workerCount; i++) {
            WarehouseUpdateWorker task = new WarehouseUpdateWorker(stockLedger, skuList, cyclesPerWorker, unitsPerCycle);
            workers[i] = new Thread(task, "RestockWorker-" + (i + 1));
            workers[i].start();
        }

        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                System.err.println("Worker synchronization interrupted.");
                Thread.currentThread().interrupt();
            }
        }

        long elapsedMicros = (System.nanoTime() - startTime) / 1000;
        int expectedUnits = initialStock + (workerCount * cyclesPerWorker * unitsPerCycle);
        double totalInventoryValue = 0.0;

        System.out.println("Expected Final Stock per SKU : " + expectedUnits + " units\n");
        for (Map.Entry<String, StockProduct> entry : productCatalog.entrySet()) {
            String sku = entry.getKey();
            StockProduct product = entry.getValue();
            int actualUnits = stockLedger.get(sku);
            double valuation = product.calculateBatchValuation(actualUnits);
            totalInventoryValue += valuation;

            System.out.println(product + " -> Final Stock: " + actualUnits +
                    " units | Valuation: $" + String.format("%,.2f", valuation));
        }

        System.out.println("\nTotal Warehouse Valuation : $" + String.format("%,.2f", totalInventoryValue));
        System.out.println("Execution Time            : " + elapsedMicros + " microseconds (Zero Data Races)");
    }
}