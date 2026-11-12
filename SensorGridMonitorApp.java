import java.util.Random;

public class SensorGridMonitorApp {
    public static void main(String[] args) {
        System.out.println("=== IoT Sensor Grid Parallel Min/Max Monitor ===\n");

        int gridSize = 100000;
        double[] telemetryData = new double[gridSize];
        Random rng = new Random(108);

        for (int i = 0; i < gridSize; i++) {
            telemetryData[i] = -15.0 + (rng.nextDouble() * 110.0);
        }

        int splitPoint = gridSize / 2;

        ParallelMinSearch minTaskLeft = new ParallelMinSearch(telemetryData, 0, splitPoint);
        ParallelMinSearch minTaskRight = new ParallelMinSearch(telemetryData, splitPoint, gridSize);
        ParallelMaxSearch maxTaskLeft = new ParallelMaxSearch(telemetryData, 0, splitPoint);
        ParallelMaxSearch maxTaskRight = new ParallelMaxSearch(telemetryData, splitPoint, gridSize);

        Thread minWorker1 = new Thread(minTaskLeft, "MinWorker-Left");
        Thread minWorker2 = new Thread(minTaskRight, "MinWorker-Right");
        Thread maxWorker1 = new Thread(maxTaskLeft, "MaxWorker-Left");
        Thread maxWorker2 = new Thread(maxTaskRight, "MaxWorker-Right");

        System.out.println("Configured 4 partition workers across split boundary index " + splitPoint + ".");
    }
}