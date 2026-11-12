public class StockProduct {
    private final String sku;
    private final String productName;
    private final double unitPrice;

    public StockProduct(String sku, String productName, double unitPrice) {
        this.sku = sku;
        this.productName = productName;
        this.unitPrice = unitPrice;
    }

    public String getSku() {
        return sku;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double calculateBatchValuation(int quantity) {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return sku + " (" + productName + " @ $" + String.format("%.2f", unitPrice) + ")";
    }
}