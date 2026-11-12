public class ReutersQuoteEvent extends TradeEvent {
    private String symbol;
    private double bidAskSpread;

    public ReutersQuoteEvent(String senderId, double basePrice, String symbol, double bidAskSpread) {
        super(senderId, basePrice);
        this.symbol = symbol;
        this.bidAskSpread = bidAskSpread;
    }

    @Override
    public void dispatch() {
        System.out.println("[REUTERS QUOTE DISPATCH] Ticker: " + symbol +
                " | Sender: " + senderId +
                " | Base Price: $" + String.format("%.2f", basePrice) +
                " | Bid-Ask Spread: $" + String.format("%.4f", bidAskSpread));
    }
}