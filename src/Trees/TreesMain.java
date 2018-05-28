package Trees;

import java.util.PriorityQueue;

public class TreesMain {
    public static void main(String[] args) {
        binarySearchTreeDemo();
        binaryHeapDemo(new BinaryHeapMin());
        priorityQueueDemo(new BinarySearchTree(1));
    }

    private static void binarySearchTreeDemo() {
        BinarySearchTree tree = new BinarySearchTree(8);
        tree.insert(3);
        tree.insert(10);
        tree.insert(1);
        tree.insert(6);
        tree.insert(4);
        tree.insert(7);
        tree.insert(14);
        tree.insert(13);

        if (tree.verify()) {
            System.out.println("Binary tree is ordered.");
        }

        tree.traverse();
        System.out.println();

        int searchCriteria = 5;
        BinarySearchTree node = tree.search(searchCriteria);
        if (node != null) {
            System.out.println(String.format("Node %s is found.", node));
        } else {
            System.out.println(String.format("Node (%d) not found", searchCriteria));
        }

        try {
            int nodeToDelete = 8;
            tree.delete(nodeToDelete);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        if (tree.verify()) {
            System.out.println("Binary tree is ordered.");
        }

        tree.traverse();
        System.out.println();
    }

    private static void binaryHeapDemo(BinaryHeap heap) {
        heap.insert(1);
        heap.insert(2);
        heap.insert(17);
        heap.insert(25);
        heap.insert(100);
        heap.insert(19);
        heap.insert(3);
        heap.insert(36);
        heap.insert(7);

        while (!heap.isEmpty()) {
            try {
                System.out.printf("%d ", heap.extract());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    private static void priorityQueueDemo(PriorityQueue queue) {
        queue.add(1);
        queue.add(2);
        queue.add(17);
        queue.add(25);
        queue.add(100);
        queue.add(19);
        queue.add(3);
        queue.add(36);
        queue.add(7);

        while (!queue.isEmpty()) {
            try {
                System.out.printf("%d ", (int) queue.poll());
            } catch (Exception e) {
                System.out.println();
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}
