package Trees;

import java.util.Arrays;

public abstract class BinaryHeap {
    private static final int DEFAULT_CAPACITY = 10;

    private int[] data;
    private int lastNode;

    public BinaryHeap() {
        data = new int[DEFAULT_CAPACITY];
        lastNode = -1;
    }

    public boolean isEmpty() {
        return lastNode == -1;
    }

    public void insert(int x) {
        lastNode++;
        if (lastNode == data.length) {
            resizeData();
        }
        data[lastNode] = x;
        heapifyUp();
    }

    public int extract() throws Exception {
        if (isEmpty()) {
            throw new Exception("Binary heap is empty.");
        }

        int best = data[0];
        data[0] = data[lastNode];
        lastNode--;
        heapifyDown();
        return best;
    }

    public int peek() throws Exception {
        if(isEmpty()) {
            throw new Exception("Binary heap is empty.");
        }
        return data[0];
    }

    protected abstract boolean isBetter(int x, int y);

    private void heapifyUp() {
        var node = lastNode;
        while (0 < node) {
            var parent = (node - 1) / 2;
            if (isBetter(data[node], data[parent])) {
                swap(node, parent);
                node = parent;
            } else {
                break;
            }
        }
    }

    private void heapifyDown() {
        var node = 0;
        while (node < lastNode) {
            var leftChild = 2 * node + 1;
            var rightChild = 2 * node + 2;
            if(lastNode<leftChild&&lastNode<rightChild){
                break;
            }
            if(leftChild<=lastNode&&lastNode<rightChild){
                if(isBetter(data[node],data[leftChild])){
                    break;
                } else{
                    swap(node, leftChild);
                    node = leftChild;
                }
            } else {
                if(isBetter(data[node], data[leftChild])&&isBetter(data[node], data[rightChild])){
                    break;
                } else {
                    if(isBetter(data[leftChild], data[rightChild])){
                        swap(node, leftChild);
                        node = leftChild;
                    } else {
                        swap(node,rightChild);
                        node = rightChild;
                    }
                }
            }
        }
    }

    private void swap(int x, int y) {
        var swap = data[x];
        data[x] = data[y];
        data[y] = swap;
    }

    private void resizeData() {
        data = Arrays.copyOf(data, data.length * 2);
    }
}
