package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations77 {

  public static void main(String[] args) {
    Combinations77 c = new Combinations77();

    c.combine(3, 2);
  }

  List<List<Integer>> res = new ArrayList<>();

  // C(n,k) n 个元素中 选出 k 个元素的组合个数
  public List<List<Integer>> combine(int n, int k) {
    if (n <= 0 || k <= 0 || k > n) {
      return res;
    }

    List<Integer> combine = new ArrayList<>();
    findCombine(n, k, 1, combine);
     res.forEach(System.out::println);
    return res;
  }


  private void findCombine(int n, int k, int index, List<Integer> combine) {
    if (k == combine.size()) {
      res.add(new ArrayList<>(combine));
      return;
    }

    while (index <= n) {
      combine.add(index);
      index++;
      findCombine(n, k, index, combine);
      combine.remove(combine.size() - 1);
    }

    // 还有k - c.size()个空位, 所以, [index...n] 中至少要有 k - c.size() 个元素
    // i最多为 n - (k - c.size()) + 1
//    while (index <= n - (k - combine.size()) + 1) {
//      combine.add(index);
//      index++;
//      findCombine(n, k, index, combine);
//      combine.remove(combine.size() - 1);
//    }

  }
}
