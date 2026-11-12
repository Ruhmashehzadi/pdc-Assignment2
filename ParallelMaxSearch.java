public class ParallelMaxSearch implements Runnable {
    private final double[] sensorReadings;
    private final int fromIndex;
    private final int toIndex;
    private double localMax;

    public ParallelMaxSearch(double[] sensorReadings, int fromIndex, int toIndex) {
        this.sensorReadings = sensorReadings;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.localMax = Double.NEGATIVE_INFINITY;
    }

    public double getLocalMax() {
        return localMax;
    }

    @Override
    public void run() {
        double currentMax = sensorReadings[fromIndex];
        for (int i = fromIndex + 1; i < toIndex; i++) {
            if (sensorReadings[i] > currentMax) {
                currentMax = sensorReadings[i];
            }
        }
        this.localMax = currentMax;
    }
}