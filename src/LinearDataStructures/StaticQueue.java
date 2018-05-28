package LinearDataStructures;

public class StaticQueue  {
    int capacity, head, tail;
    int[] data;

    public StaticQueue() {
        capacity = 10;
        head = tail = 0;
        int[] data = new int[capacity];
    }

    public boolean isEmpty() {
        return tail==head;
    }

    public void enqueue(int x) {
        tail++;
        tail%=capacity;
        if(head==tail){
            resize();
        }
        data[tail]=x;
    }

    public int dequeue() throws Exception {
        if(isEmpty()) throw new Exception("Queue is empty.");
        return data[head++];
    }

    public int peek() throws Exception {
        if(isEmpty()) throw new Exception("Queue is empty.");
        return data[head+1];
    }

    private void resize() {
        int[] newData = new int[capacity*2];
        for (int i = 0; i < capacity; i++) {
            newData[i] = data[head];
            head++;
            head %= capacity;
        }
        head = 0;
        tail = capacity;
        capacity*=2;
        data = newData;
    }
}
