package unionset;

public class QuickUnion {
    int size = 0;
//    int[] item;
    int[] parent;

    public QuickUnion(int size) {
        this.size = size;
//        item = new int[size];
        parent = new int[size];

        for (int i = 0; i < size; i++) { // initial
            parent[i] = i;
        }
    }

    public void unionItem(int a, int b) {

    }

    public void isConnected(int a, int b) {

    }

    private void findParent(int i) {

    }
}
