public class TelemetryThread extends Thread {
    private int sampleCycles;

    public TelemetryThread(String threadName, int sampleCycles) {
        super(threadName);
        this.sampleCycles = sampleCycles;
    }
}