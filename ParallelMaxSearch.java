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

    @Override
    public void run() {
    }
}