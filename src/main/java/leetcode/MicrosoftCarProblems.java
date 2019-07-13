package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MicrosoftCarProblems {

  public static void main(String[] args) {
    Integer[] ar1 = new Integer[]{2, 3};
    Integer[] ar2 = new Integer[]{1, 4};
    ArrayList<ArrayList<Integer>> list = new ArrayList<>();

    list.add(new ArrayList<>(Arrays.asList(ar1)));
    list.add(new ArrayList<>(Arrays.asList(ar2)));
    getAllSolution(list, 4);
  }

  static List<ArrayList<ArrayList<Integer>>> getAllSolution(
      ArrayList<ArrayList<Integer>> list, int n) {

    List<ArrayList<ArrayList<Integer>>> res = new ArrayList<ArrayList<ArrayList<Integer>>>();

    int[] begins = new int[list
        .size()]; // the begin index of each car segments
    begins[0] = 0;
    for (int i = 1; i < begins.length; i++) {
      begins[i] =
          list.get(i).size() + begins[i - 1];
    }

    // when insert car is at the first
    ArrayList<ArrayList<Integer>> fristList = copayList(
        list);
    ArrayList<Integer> firstSeg = new ArrayList<>();
    firstSeg.add(Integer.MAX_VALUE);
    fristList.add(0, firstSeg);
    res.add(fristList);

    // others kind
    for (int i = 1; i <= n; i++) {
      List<ArrayList<Integer>> tempList = copayList(
          list);
      res.add(insertCar(begins, i, tempList));
    }
    return res;
  }

  static ArrayList<ArrayList<Integer>> insertCar(
      int[] begins, int index,
      List<ArrayList<Integer>> list) {

    if (index < begins[0]) {
      list.get(0).add(index, Integer.MAX_VALUE);
    }

    int lastSegment = begins.length - 1;

    for (int i = 1; i <= lastSegment; i++) {
      if (index > begins[i - 1] && index
          <= begins[i]) { // between begins[i - 1] and begins[i]
        list.get(i - 1).add(index - begins[i - 1],
            Integer.MAX_VALUE);
        break;
      } else if (index
          > begins[lastSegment]) { // in the last segment
        list.get(lastSegment)
            .add(index - begins[lastSegment],
                Integer.MAX_VALUE);
        break;
      }
    }
    return new ArrayList<>(list);
  }

  static ArrayList<ArrayList<Integer>> copayList(
      ArrayList<ArrayList<Integer>> list) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    for (ArrayList<Integer> l : list) {
      res.add(new ArrayList<>(l));
    }
    return res;
  }
}
