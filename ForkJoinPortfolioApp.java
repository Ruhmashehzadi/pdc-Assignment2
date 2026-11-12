import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinPortfolioApp {
    public static void main(String[] args) {
        System.out.println("=== Fork/Join Asset Portfolio Risk & Stress Engine ===\n");

        int totalAssets = 10000;
        double[] portfolio = new double[totalAssets];
        Random rng = new Random(42);

        for (int i = 0; i < totalAssets; i++) {
            portfolio[i] = 1500.0 + (rng.nextDouble() * 8500.0);
        }

        ForkJoinPool workerPool = new ForkJoinPool();
        System.out.println("Active ForkJoinPool Parallelism Level: " + workerPool.getParallelism() + " cores\n");

        long startTime = System.nanoTime();

        ParallelRiskCalculator baselineTask = new ParallelRiskCalculator(portfolio, 0, totalAssets);
        double baselineRisk = workerPool.invoke(baselineTask);
        System.out.println("Baseline Portfolio VaR (Risk Score): $" + String.format("%,.2f", baselineRisk));

        double exposureThreshold = 7500.0;
        PortfolioThresholdTask stressTask = new PortfolioThresholdTask(portfolio, 0, totalAssets, exposureThreshold);
        workerPool.invoke(stressTask);
        System.out.println("Applied 5% stress volatility cap on assets exceeding $" + exposureThreshold);

        ParallelRiskCalculator adjustedTask = new ParallelRiskCalculator(portfolio, 0, totalAssets);
        double postStressRisk = workerPool.invoke(adjustedTask);

        long elapsedMicros = (System.nanoTime() - startTime) / 1000;
        workerPool.shutdown();

        System.out.println("Post-Stress Portfolio VaR (Risk Score): $" + String.format("%,.2f", postStressRisk));
        System.out.println("Risk Reduction Delta: $" + String.format("%,.2f", (baselineRisk - postStressRisk)));
        System.out.println("\nFork/Join execution completed in " + elapsedMicros + " microseconds.");
    }
}