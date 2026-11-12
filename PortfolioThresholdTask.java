import java.util.concurrent.RecursiveAction;

public class PortfolioThresholdTask extends RecursiveAction {
    private static final int SEGMENT_LIMIT = 1000;
    private final double[] assetHoldings;
    private final int startIdx;
    private final int endIdx;
    private final double exposureCap;

    public PortfolioThresholdTask(double[] assetHoldings, int startIdx, int endIdx, double exposureCap) {
        this.assetHoldings = assetHoldings;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
        this.exposureCap = exposureCap;
    }

    @Override
    protected void compute() {
        int span = endIdx - startIdx;
        if (span <= SEGMENT_LIMIT) {
            for (int i = startIdx; i < endIdx; i++) {
                if (assetHoldings[i] > exposureCap) {
                    assetHoldings[i] *= 0.95; // Apply 5% stress volatility adjustment
                }
            }
            return;
        }

        int midIdx = startIdx + (span / 2);
        PortfolioThresholdTask leftSegment = new PortfolioThresholdTask(assetHoldings, startIdx, midIdx, exposureCap);
        PortfolioThresholdTask rightSegment = new PortfolioThresholdTask(assetHoldings, midIdx, endIdx, exposureCap);

        invokeAll(leftSegment, rightSegment);
    }
}