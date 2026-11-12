public class PacketSymmetryAuditor {
    public static boolean auditPayload(String payload) {
        if (payload == null || payload.isEmpty()) {
            return false;
        }

        String normalized = payload.toUpperCase();
        int len = normalized.length();

        TransactionStack stack = new TransactionStack(len);
        RequestQueue queue = new RequestQueue(len);

        for (int i = 0; i < len; i++) {
            char ch = normalized.charAt(i);
            stack.push(ch);
            queue.enqueue(ch);
        }

        while (!stack.isEmpty() && !queue.isEmpty()) {
            if (stack.pop() != queue.dequeue()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("=== Zero-Trust Packet Symmetry Auditor ===\n");

        String[] testPayloads = {"RADAR", "PKT909TKP", "NET_SYNC", "LEVEL", "101101", "CORRUPT_X"};

        for (String packet : testPayloads) {
            boolean isSymmetric = auditPayload(packet);
            String status = isSymmetric ? "VERIFIED (Symmetric)" : "REJECTED (Asymmetric/Tampered)";
            System.out.println("Payload: " + String.format("%-12s", packet) + " -> Status: " + status);
        }
    }
}