public class FIXOrderEvent extends TradeEvent {
    private String orderId;
    private int quantity;

    public FIXOrderEvent(String senderId, double basePrice, String orderId, int quantity) {
        super(senderId, basePrice);
        this.orderId = orderId;
        this.quantity = quantity;
    }

    @Override
    public void dispatch() {
        double totalValue = basePrice * quantity;
        System.out.println("[FIX ORDER DISPATCH] Order ID: " + orderId +
                " | Sender: " + senderId +
                " | Qty: " + quantity +
                " | Total Trade Value: $" + String.format("%.2f", totalValue));
    }
}