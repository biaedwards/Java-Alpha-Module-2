package Trees;

import java.util.PriorityQueue;

class BinarySearchTree extends PriorityQueue {
    private int value;
    private BinarySearchTree left, right;

    BinarySearchTree(int value) {
        this.value = value;
        left = right = null;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean isEmpty(){
        return false;
    }

    @Override
    public String toString() {
        return String.format("(%d)", value);
    }

    public BinarySearchTree search(int x) {
        if (x == value) return this;
        if (x < value && left != null) return left.search(x);
        if (x > value && right != null) return right.search(x);
        return null;
    }

    public BinarySearchTree insert(int x) {
        if (x <= value) {
            if (left != null) return left.insert(x);
            left = new BinarySearchTree(x);
            return left;
        } else {
            if (right != null) return right.insert(x);
            right = new BinarySearchTree(x);
            return right;
        }
    }

    public void delete(int x) {
        delete(null, x);
    }

    public void traverse() {
        if (left != null) left.traverse();
        System.out.printf("%d ", value);
        if (right != null) right.traverse();
    }

    public boolean verify() {
        if ((left != null && left.getValue() > value) || (right != null && right.getValue() <= value)) return false;
        return (left == null || left.verify()) && (right == null || right.verify());
    }

    public void add(int x) {
        if (x <= value) {
            if (left == null) {
                left = new BinarySearchTree(x);
                return;
            }
            left.add(x);
        } else {
            if (right == null) {
                right = new BinarySearchTree(x);
                return;
            }
            right.add(x);
        }
    }

    @Override
    public Object poll() {
        var maxNode = getMax(this);
        var result = maxNode.value;
        delete(result);
        return result;
    }

    public int priorityPeek() {
        return getMax(this).getValue();
    }

    public void delete(BinarySearchTree parent, int x) {
        if (x == value) {
            if (left == null && right == null) {
                if (parent.value >= this.value) parent.left = null;
                else parent.right = null;
                return;
            }
            if (left != null && right == null) {
                value = left.value;
                left = left.left;
                right = left.right;
                return;
            }
            if (left == null && right != null) {
                value = right.value;
                left = right.left;
                right = right.right;
                return;
            }
            var maxLeft = getMax(left);
            var maxLeftValue = maxLeft.value;
            delete(parent, maxLeftValue);
            value = maxLeftValue;
        } else {
            if (x < value && left != null) {
                left.delete(this, x);
            } else if (x > value && right != null) {
                right.delete(this, x);
            }
        }
    }


    public BinarySearchTree getMax(BinarySearchTree node) {
        if (right != null) return getMax(node.right);
        return node;
    }
}
