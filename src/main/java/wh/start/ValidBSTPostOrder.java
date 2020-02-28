package wh.start;

public class ValidBSTPostOrder {

  // ebay
  public static void main(String[] args) {
    ValidBSTPostOrder p = new ValidBSTPostOrder();
    int[] oo = new int[]{9, 10, 8, 15, 19, 11, 12};
    System.out.println(p.isValidBSTPostOrder(oo, oo.length));
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
    if (l == r || r < 0 || l < 0) return true;

    int root = x[r];
    int k = -1;
    for (int i = l; i < r; i++) {
      // (k != -1 && x[i] < root) 找到k点之后 还有< root 的点
      if (x[i] < root && root < x[i + 1] || (k != -1 && x[i] < root)) {
        if (k == -1) {
          k = i;
        } else {
          return false;
        }
      }
    }

    boolean res1 = true;
    boolean res2 = true;

    if (k != -1) { // 找到k点
      res1 = _isValidBSTPostOrder(x, l, k);
      res2 = _isValidBSTPostOrder(x, k + 1, r - 1);
    } else {
      // 没有找到
      if (x[l] < root) {
        res1 = _isValidBSTPostOrder(x, l, r - 1);
      }
      if (x[l] > root) {
        res2 = _isValidBSTPostOrder(x, l, r - 1);
      }
    }
    return res1 && res2;

  }
}
