import java.util.Random;

public class ForkJoinPortfolioApp {
    public static void main(String[] args) {
        System.out.println("=== Fork/Join Asset Portfolio Risk & Stress Engine ===\n");

        int totalAssets = 10000;
        double[] portfolio = new double[totalAssets];
        Random rng = new Random(42);

        for (int i = 0; i < totalAssets; i++) {
            portfolio[i] = 1500.0 + (rng.nextDouble() * 8500.0);
        }

        System.out.println("Initialized synthetic portfolio with " + totalAssets + " assets.");
    }
}