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

        ParallelRiskCalculator baselineTask = new ParallelRiskCalculator(portfolio, 0, totalAssets);
        double baselineRisk = workerPool.invoke(baselineTask);
        System.out.println("Baseline Portfolio VaR (Risk Score): $" + String.format("%,.2f", baselineRisk));
    }
}