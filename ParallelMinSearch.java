public class ParallelMinSearch implements Runnable {
    private final double[] sensorReadings;
    private final int fromIndex;
    private final int toIndex;
    private double localMin;

    public ParallelMinSearch(double[] sensorReadings, int fromIndex, int toIndex) {
        this.sensorReadings = sensorReadings;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.localMin = Double.POSITIVE_INFINITY;
    }

    public double getLocalMin() {
        return localMin;
    }

    @Override
    public void run() {
        double currentMin = sensorReadings[fromIndex];
        for (int i = fromIndex + 1; i < toIndex; i++) {
            if (sensorReadings[i] < currentMin) {
                currentMin = sensorReadings[i];
            }
        }
        this.localMin = currentMin;
    }
}