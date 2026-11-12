import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class WarehouseUpdateWorker implements Runnable {
    private final ConcurrentHashMap<String, Integer> stockLedger;
    private final List<String> targetSkus;
    private final int restockCycles;
    private final int unitsPerCycle;

    public WarehouseUpdateWorker(ConcurrentHashMap<String, Integer> stockLedger,
                                 List<String> targetSkus,
                                 int restockCycles,
                                 int unitsPerCycle) {
        this.stockLedger = stockLedger;
        this.targetSkus = targetSkus;
        this.restockCycles = restockCycles;
        this.unitsPerCycle = unitsPerCycle;
    }

    @Override
    public void run() {
        for (int cycle = 0; cycle < restockCycles; cycle++) {
            for (String sku : targetSkus) {
                stockLedger.merge(sku, unitsPerCycle, Integer::sum);
            }
        }
    }
}