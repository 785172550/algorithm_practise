"""
Given an integer array nums, return the length of the longest strictly increasing subsequence.

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1

Constraints:
1 <= nums.length <= 2500
-10(4) <= nums[i] <= 10(4)

"""

"""

status change eqation:

LIS(n) represents the LIS that ends with nums[n].
LIS(n) = max(1, 1+ LIS(j)), where j in [0, 1, ..., n-1]
"""

from typing import List


def length_of_lis(nums: List[int]) -> int:
    """
    time complexity: O(n^2)
    """
    if not nums:
        return 0
    if len(nums) == 1:
        return 1

    dp = [1] * len(nums)

    # [10,9,2,5,3,7,101,18]
    for i in range(len(nums)):
        for j in range(i):

            # If nums[i] is greater than nums[j], it can extend the increasing subsequence
            if nums[i] > nums[j]:
                dp[i] = max(dp[i], dp[j] + 1)

    # print(dp)
    return max(dp)


#  memoization approach
def length_of_lis_recur(nums: List[int]) -> int:
    memo = [-1] * len(nums)

    # Using a helper function to recursively find the LIS
    helper(nums, memo, len(nums) - 1)

    return max(memo)


def helper(nums: List[int], memo: List[int], index: int) -> int:
    if index == 0:
        return 1

    if memo[index] != -1:
        return memo[index]

    cur_lis_len = 1  # Initialize the LIS ending at index to 1

    for i in range(index):
        if nums[index] > nums[i]:
            cur_lis_len = max(cur_lis_len, helper(nums, memo, i) + 1)

    memo[index] = cur_lis_len  # Store the result in memo
    return memo[index]


# Patience Sorting, poker牌分堆法
def length_of_lis2(nums: List[int]) -> int:
    """
    time complexity: O(n log n)
    """
    if not nums:
        return 0
    if len(nums) == 1:
        return 1

    # This will store the smallest tail of all increasing subsequences of different lengths
    # memo is a ordered list
    memo = []

    for num in nums:  # Iterate through each number in nums
        left, right = 0, len(memo)

        # Find the position using binary search
        left_pos = find_largest_bfs(left, right, num, memo)

        # if left_pos == right(len(memo)), it means num is larger than all elements in memo
        if left_pos == len(memo):
            memo.append(num)
        else:
            memo[left_pos] = num  # otherwise, replace the element at left_pos with num

    return len(memo)


def find_largest_bfs(left, right, target, memo) -> int:

    # [left, right), return when left == right
    while left < right:
        mid = (left + right) // 2

        if (
            memo[mid] < target
        ):  # If the middle element is less than target, we need to search in the right half
            left = mid + 1
        else:
            right = mid  # If the middle element is greater than or equal to target, we search in the left half

    return left


if __name__ == "__main__":
    print(length_of_lis([10, 9, 2, 5, 3, 7, 101, 18]))
    print(length_of_lis([0, 1, 0, 3, 2, 3]))
    print(length_of_lis([7, 7, 7, 7, 7, 7, 7]))
    print(length_of_lis([1]))
    print(length_of_lis([]))

    print("Using binary search:")
    print(length_of_lis2([10, 9, 2, 5, 3, 7, 101, 18]))
    print(length_of_lis2([0, 1, 0, 3, 2, 3]))
    print(length_of_lis2([7, 7, 7, 7, 7, 7, 7]))
    print(length_of_lis2([1]))
    print(length_of_lis2([]))


"""
类似问题：
计算最长递减子序列（LDS）​
计算最长公共子序列（LCS）​
计算最长上升子序列的数量​
计算最优任务调度（贪心策略）​
计算股票交易的最佳时机（贪心 + LIS）​
计算最小编辑距离（动态规划优化）​

"""
