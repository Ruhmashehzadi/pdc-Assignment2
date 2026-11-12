import java.util.ArrayList;
import java.util.List;

public class TradingEngineApp {
    public static void main(String[] args) {
        System.out.println("=== Financial Complex Event Processing System ===\n");

        List<TradeEvent> eventQueue = new ArrayList<>();

        eventQueue.add(new FIXOrderEvent("TRADER_DESK_1", 154.50, "ORD-8821", 500));
        eventQueue.add(new ReutersQuoteEvent("REUTERS_FEED", 2750.25, "GOOGL", 0.0512));
        eventQueue.add(new FIXOrderEvent("TRADER_DESK_2", 89.10, "ORD-8822", 1200));
        eventQueue.add(new ReutersQuoteEvent("BLOOMBERG_FEED", 412.80, "MSFT", 0.0250));

        System.out.println("Processing polymorphic trade events via dynamic dispatch...\n");
        for (TradeEvent event : eventQueue) {
            event.dispatch();
        }
        System.out.println("\nAll event processing pipeline completed.");
    }
}