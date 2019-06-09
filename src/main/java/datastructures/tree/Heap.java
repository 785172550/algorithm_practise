package datastructures.tree;

public class Heap {

    int[] arr; // 完全二叉树
    int size;
    int index;

    public Heap(int n) {
        arr = new int[n + 1];
        size = n;
        index = 1;
    }

    public void put(int e) {
        if (index > size)
            return;

        arr[index] = e;
        index++;
        shiftUp(index);
    }

    private void shiftUp(int index) {
        while (index > 1 && arr[index / 2] > arr[index]) {
//            swap()
            index = index / 2;
        }

    }

    private void shifDown(int e) {

    }
}
