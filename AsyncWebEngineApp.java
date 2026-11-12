public class AsyncWebEngineApp {
    public static void main(String[] args) {
        System.out.println("=== Asynchronous Multithreaded Web Engine ===\n");

        TelemetryThread telemetryWorker = new TelemetryThread("Telemetry-Daemon", 4);
        Thread logWorker = new Thread(new LogProcessorTask("Async-Log-Writer", 4));

        System.out.println("Initial State (" + telemetryWorker.getName() + "): " + telemetryWorker.getState());
        System.out.println("Initial State (Async-Log-Writer): " + logWorker.getState() + "\n");

        long startTime = System.currentTimeMillis();

        telemetryWorker.start();
        logWorker.start();

        System.out.println("Post-Start State (" + telemetryWorker.getName() + "): " + telemetryWorker.getState());
        System.out.println("Post-Start State (Async-Log-Writer): " + logWorker.getState() + "\n");

        try {
            telemetryWorker.join();
            logWorker.join();
        } catch (InterruptedException e) {
            System.err.println("Main coordinator thread interrupted while waiting.");
            Thread.currentThread().interrupt();
        }

        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("\nFinal State (" + telemetryWorker.getName() + "): " + telemetryWorker.getState());
        System.out.println("Final State (Async-Log-Writer): " + logWorker.getState());
        System.out.println("All background tasks synchronized and completed in " + elapsed + " ms.");
    }
}