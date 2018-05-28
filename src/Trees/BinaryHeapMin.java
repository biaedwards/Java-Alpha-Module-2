package Trees;

public class BinaryHeapMin extends BinaryHeap {
    @Override
    protected boolean isBetter(int x, int y) {
        return x < y;
    }
}
