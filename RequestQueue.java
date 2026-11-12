public class RequestQueue {
    private char[] buffer;
    private int front;
    private int rear;
    private int count;
    private int maxCapacity;

    public RequestQueue(int capacity) {
        this.maxCapacity = capacity;
        this.buffer = new char[capacity];
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == maxCapacity;
    }

    public void enqueue(char ch) {
        if (isFull()) {
            throw new IllegalStateException("RequestQueue is full");
        }
        rear = (rear + 1) % maxCapacity;
        buffer[rear] = ch;
        count++;
    }

    public char dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("RequestQueue is empty");
        }
        char item = buffer[front];
        front = (front + 1) % maxCapacity;
        count--;
        return item;
    }
}