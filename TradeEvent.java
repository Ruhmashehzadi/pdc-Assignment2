import java.time.LocalDateTime;

public abstract class TradeEvent {
    protected String senderId;
    protected LocalDateTime timestamp;
    protected double basePrice;
}