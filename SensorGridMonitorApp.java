import java.util.Random;

public class SensorGridMonitorApp {
    public static void main(String[] args) {
        System.out.println("=== IoT Sensor Grid Parallel Min/Max Reduction Engine ===\n");

        int totalReadings = 100000;
        double[] telemetryStream = new double[totalReadings];
        Random rng = new Random(108);

        for (int i = 0; i < totalReadings; i++) {
            telemetryStream[i] = -15.0 + (rng.nextDouble() * 110.0);
        }

        int midPoint = totalReadings / 2;

        ParallelMinSearch minTaskLeft = new ParallelMinSearch(telemetryStream, 0, midPoint);
        ParallelMinSearch minTaskRight = new ParallelMinSearch(telemetryStream, midPoint, totalReadings);
        ParallelMaxSearch maxTaskLeft = new ParallelMaxSearch(telemetryStream, 0, midPoint);
        ParallelMaxSearch maxTaskRight = new ParallelMaxSearch(telemetryStream, midPoint, totalReadings);

        Thread minWorker1 = new Thread(minTaskLeft, "MinWorker-Left");
        Thread minWorker2 = new Thread(minTaskRight, "MinWorker-Right");
        Thread maxWorker1 = new Thread(maxTaskLeft, "MaxWorker-Left");
        Thread maxWorker2 = new Thread(maxTaskRight, "MaxWorker-Right");

        long parallelStart = System.nanoTime();

        minWorker1.start();
        minWorker2.start();
        maxWorker1.start();
        maxWorker2.start();

        try {
            minWorker1.join();
            minWorker2.join();
            maxWorker1.join();
            maxWorker2.join();
        } catch (InterruptedException e) {
            System.err.println("Sensor reduction barrier interrupted.");
            Thread.currentThread().interrupt();
        }

        double globalMin = Math.min(minTaskLeft.getLocalMin(), minTaskRight.getLocalMin());
        double globalMax = Math.max(maxTaskLeft.getLocalMax(), maxTaskRight.getLocalMax());
        long parallelMicros = (System.nanoTime() - parallelStart) / 1000;

        // Sequential sanity check
        double seqMin = telemetryStream[0];
        double seqMax = telemetryStream[0];
        for (int i = 1; i < totalReadings; i++) {
            if (telemetryStream[i] < seqMin) seqMin = telemetryStream[i];
            if (telemetryStream[i] > seqMax) seqMax = telemetryStream[i];
        }

        System.out.println("Parallel Global Minimum Reading : " + String.format("%.4f", globalMin) + " C");
        System.out.println("Parallel Global Maximum Reading : " + String.format("%.4f", globalMax) + " C");
        System.out.println("Parallel Reduction Time         : " + parallelMicros + " microseconds");
        System.out.println("Sequential Accuracy Match       : " + (globalMin == seqMin && globalMax == seqMax));
    }
}