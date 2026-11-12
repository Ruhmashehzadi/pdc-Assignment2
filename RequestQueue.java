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
}