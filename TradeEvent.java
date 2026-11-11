import java.time.LocalDateTime;

public abstract class TradeEvent {
    protected String senderId;
    protected LocalDateTime timestamp;
    protected double basePrice;

    public TradeEvent(String senderId, double basePrice) {
        this.senderId = senderId;
        this.timestamp = LocalDateTime.now();
        this.basePrice = basePrice;
    }

    public String getSenderId() { return senderId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public double getBasePrice() { return basePrice; }
}