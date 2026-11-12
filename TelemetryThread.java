public class TelemetryThread extends Thread {
    private int sampleCycles;

    public TelemetryThread(String threadName, int sampleCycles) {
        super(threadName);
        this.sampleCycles = sampleCycles;
    }

    @Override
    public void run() {
        System.out.println("[" + getName() + "] Telemetry daemon active...");
        for (int i = 1; i <= sampleCycles; i++) {
            try {
                Thread.sleep(600);
                double cpuLoad = 22.5 + (i * 4.3);
                int memUsedMb = 512 + (i * 48);
                System.out.println("[" + getName() + "] Sample " + i +
                        " -> CPU Load: " + String.format("%.1f", cpuLoad) + "% | Heap: " + memUsedMb + " MB");
            } catch (InterruptedException e) {
                System.err.println("[" + getName() + "] Telemetry sampling interrupted.");
                interrupt();
                break;
            }
        }
        System.out.println("[" + getName() + "] Telemetry daemon stopped.");
    }
}