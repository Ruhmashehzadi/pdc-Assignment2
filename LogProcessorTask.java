public class LogProcessorTask implements Runnable {
    private String workerName;
    private int batchCount;

    public LogProcessorTask(String workerName, int batchCount) {
        this.workerName = workerName;
        this.batchCount = batchCount;
    }

    @Override
    public void run() {
        System.out.println("[" + workerName + "] Started asynchronous log ingestion...");
        for (int i = 1; i <= batchCount; i++) {
            try {
                Thread.sleep(700);
                System.out.println("[" + workerName + "] Flushed log batch #" + i + " to disk.");
            } catch (InterruptedException e) {
                System.err.println("[" + workerName + "] Interrupted during log flush.");
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("[" + workerName + "] Completed all log batches.");
    }
}