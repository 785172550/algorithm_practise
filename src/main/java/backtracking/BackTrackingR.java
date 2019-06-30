package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTrackingR {
    public static void main(String[] args) {
        BackTrackingR btr = new BackTrackingR();
        btr.subsets(new int[]{1, 3, 2});
    }

    // leetcode 78
    // Given a set of distinct integers, nums, return all possible subsets (the power set)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
//        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
//        list.forEach(System.out::println);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    // leetcode 90 sbuset II
    // Given a collection of integers that might contain duplicates, nums, return all possible subsets
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack2(list, new ArrayList<>(), nums, 0);
        list.forEach(System.out::println);
        return list;
    }

    private void backtrack2(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            if (start != i && nums[i] == nums[i - 1]) continue;// skip duplicates
            tempList.add(nums[i]);
            backtrack2(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    // leetcode 46. Permutations
    // Given a collection of distinct integers, return all possible permutations.
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack3(list, new ArrayList<>(), used, nums, 0);
        return list;
    }

    private void backtrack3(List<List<Integer>> list, List<Integer> tempList, boolean[] used, int[] nums, int start) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            tempList.add(nums[i]);
            used[i] = true;
            backtrack3(list, tempList, used, nums, i + 1);
            tempList.remove(tempList.size() - 1);
            used[i] = false;
        }
    }
}
