import java.util.concurrent.RecursiveTask;

public class ParallelRiskCalculator extends RecursiveTask<Double> {
    private static final int BATCH_THRESHOLD = 1000;
    private final double[] assetHoldings;
    private final int startIdx;
    private final int endIdx;

    public ParallelRiskCalculator(double[] assetHoldings, int startIdx, int endIdx) {
        this.assetHoldings = assetHoldings;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }

    private double computeRiskChunk() {
        double weightedRisk = 0.0;
        for (int i = startIdx; i < endIdx; i++) {
            weightedRisk += assetHoldings[i] * 0.042;
        }
        return weightedRisk;
    }

    @Override
    protected Double compute() {
        int span = endIdx - startIdx;
        if (span <= BATCH_THRESHOLD) {
            return computeRiskChunk();
        }

        int midIdx = startIdx + (span / 2);
        ParallelRiskCalculator leftBranch = new ParallelRiskCalculator(assetHoldings, startIdx, midIdx);
        ParallelRiskCalculator rightBranch = new ParallelRiskCalculator(assetHoldings, midIdx, endIdx);

        leftBranch.fork();
        double rightRisk = rightBranch.compute();
        double leftRisk = leftBranch.join();

        return leftRisk + rightRisk;
    }
}