public class TransactionStack {
    private char[] elements;
    private int top;
    private int maxCapacity;

    public TransactionStack(int capacity) {
        this.maxCapacity = capacity;
        this.elements = new char[capacity];
        this.top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxCapacity - 1;
    }
}