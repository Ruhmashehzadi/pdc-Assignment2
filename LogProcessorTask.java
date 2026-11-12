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
        System.out.println("[" + workerName + "] Completed all log batches.");
    }
}