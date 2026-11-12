public class StockProduct {
    private final String sku;
    private final String productName;
    private final double unitPrice;

    public StockProduct(String sku, String productName, double unitPrice) {
        this.sku = sku;
        this.productName = productName;
        this.unitPrice = unitPrice;
    }
}