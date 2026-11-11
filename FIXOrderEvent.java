public class FIXOrderEvent extends TradeEvent {
    private String orderId;
    private int quantity;

    public FIXOrderEvent(String senderId, double basePrice, String orderId, int quantity) {
        super(senderId, basePrice);
        this.orderId = orderId;
        this.quantity = quantity;
    }
}