public class AsyncWebEngineApp {
    public static void main(String[] args) {
        System.out.println("=== Asynchronous Multithreaded Web Engine ===\n");

        TelemetryThread telemetryWorker = new TelemetryThread("Telemetry-Daemon", 4);
        Thread logWorker = new Thread(new LogProcessorTask("Async-Log-Writer", 4));

        System.out.println("Initial State (" + telemetryWorker.getName() + "): " + telemetryWorker.getState());
        System.out.println("Initial State (Async-Log-Writer): " + logWorker.getState() + "\n");
    }
}