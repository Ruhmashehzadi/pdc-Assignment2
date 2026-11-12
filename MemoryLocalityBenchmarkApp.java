public class MemoryLocalityBenchmarkApp {
    public static void main(String[] args) {
        System.out.println("=== CPU Spatial Cache Locality & Matrix Traversal Benchmark ===\n");

        int matrixSize = 2048;
        long totalCells = (long) matrixSize * matrixSize;

        System.out.println("Allocating " + matrixSize + " x " + matrixSize + " matrix (" + totalCells + " double elements)...");
        GridTraversalEngine engine = new GridTraversalEngine(matrixSize);
        System.out.println("Matrix memory allocation complete.\n");

        // JVM JIT warm-up cycle
        double warmupSink = engine.computeRowMajorSum() + engine.computeColumnMajorSum();
        if (warmupSink == -1.0) {
            System.out.println("Unreachable guard");
        }

        long rowStart = System.nanoTime();
        double rowSum = engine.computeRowMajorSum();
        long rowEnd = System.nanoTime();
        double rowDurationMs = (rowEnd - rowStart) / 1_000_000.0;

        System.out.println("[Row-Major Traversal]    Time: " + String.format("%.3f", rowDurationMs) + " ms | Checksum: " + String.format("%.2f", rowSum));
    }
}