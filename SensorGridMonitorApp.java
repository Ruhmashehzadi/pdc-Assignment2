import java.util.Random;

public class SensorGridMonitorApp {
    public static void main(String[] args) {
        System.out.println("=== IoT Sensor Grid Parallel Min/Max Monitor ===\n");

        int gridSize = 100000;
        double[] telemetryData = new double[gridSize];
        Random rng = new Random(108);

        for (int i = 0; i < gridSize; i++) {
            telemetryData[i] = -15.0 + (rng.nextDouble() * 110.0); // Range: -15.0°C to +95.0°C
        }

        System.out.println("Loaded " + gridSize + " IoT sensor telemetry readings into memory.");
    }
}