package wh.start;

public class ValidBSTPostOrder {

  // ebay
  public static void main(String[] args) {

  }


  /**
   * PostOrderTraversal: [9, 10, 8, 15, 19, 16, 12]
   * <p>
   * Boolean isValidBSTPostOrder(int[] x, int len)
   */
  Boolean isValidBSTPostOrder(int[] x, int len) {
    return _isValidBSTPostOrder(x, 0, len - 1);
  }

  private Boolean _isValidBSTPostOrder(int[] x, int l, int r) {
    if (l == r) return true;

    int root = x[r];
    int k = -1;
    for (int i = l; i < r; i++) {
      if (x[i - 1] < root && root < x[i]) {
        if (k == -1) {
          k = i - 1;
        } else {
          return false;
        }
      }
    }
    boolean res1 = _isValidBSTPostOrder(x, l, k);
    boolean res2 = _isValidBSTPostOrder(x, k + 1, r);
    return res1 && res2;

  }
}
