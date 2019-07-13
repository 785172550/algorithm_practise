package tree.heap;

public class IndexMinHeapTemplate<Item extends Comparable<?>> {
    Item data[];
    int index[]; // index[0] = 10 表示 data[10] 的数据应该排在 第 0 的位置
    int reverse[]; // 反向索引 reverse[i] = x 表示索引i在x的位置
    int count;
    int capacity;

    public IndexMinHeapTemplate(int capacity) {
        this.capacity = capacity;
        data = (Item[]) new Comparable[capacity];
        count = 0;
    }
}
