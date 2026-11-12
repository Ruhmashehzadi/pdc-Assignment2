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
    }
}