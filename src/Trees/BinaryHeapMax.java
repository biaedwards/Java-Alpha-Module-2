package Trees;

public class BinaryHeapMax extends BinaryHeap {
    @Override
    protected boolean isBetter(int x, int y) {
        return x > y;
    }
}
