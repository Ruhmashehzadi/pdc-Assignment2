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
}