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
    # memo is a ordered list, we can binary search
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

        # If the middle element is less than target, search in the right half
        if memo[mid] < target:
            left = mid + 1
        else:
            # search in the left half
            right = mid

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
hard level if using patience sorting, if bare dynamic programming, it is medium level.

类似问题：
计算最长递减子序列（LDS）​
计算最长公共子序列（LCS）​
计算最长上升子序列的数量​
计算最优任务调度（贪心策略）​
计算股票交易的最佳时机（贪心 + LIS）​
计算最小编辑距离（动态规划优化）​


300. Longest Increasing Subsequence
354. Russian Doll Envelopes (hard)
1713. Minimum Operations to Make a Subsequence (hard)
"""

"""

如带容量限制的匹配问题，多个实验需要在一段时间内分配用户流量。
每个实验有 quota、priority 和起止时间。系统每天的 capacity 有限，目标是按优先级公平分配流量。
目标是公平地、尽可能满足 quota、并按 priority 分配。

每个实验或者计算的属性:

- quota: The total amount of traffic required by quota (e.g. 100K users).
- priority: priority (higher priority experiments are allocated first).
- start_time/end_time: the time window in which the experiment will run.

- represented:
(quota, priority, start_time, end_time) = (100, 1, 0, 3)

experiments = [(100, 1, 0, 3), (80, 2, 1, 4), (50, 1, 2, 5)]

capacity limited to 100K users per day.

-----------
思路，使用贪心算法 + 优先级队列​​ 来分配流量，满足以下目标
以下是 Python 实现，使用 ​​贪心算法 + 优先级队列​​ 来分配流量，满足以下目标：

​高优先级实验优先分配​​
​公平性​​（避免低优先级实验饿死）
​​尽可能满足 quota
按时间窗口分配​

"""

from dataclasses import dataclass
from typing import List
import heapq


@dataclass
class Experiment:
    id: str  # 实验ID
    priority: int  # 优先级（越高越优先）
    quota: int  # 需要的总流量
    start_day: int  # 开始天数
    end_day: int  # 结束天数


@dataclass
class Allocation:
    experiment_id: str
    day: int
    assigned: int  # 当天分配的流量


import heapq
from dataclasses import dataclass
from typing import List


@dataclass
class Experiment:
    id: str
    priority: int  # 优先级（值越大越优先）
    quota: int
    start_day: int
    end_day: int


@dataclass
class Allocation:
    experiment_id: str
    day: int
    assigned: int


def allocate_traffic(
    experiments: List[Experiment], daily_capacity: int, max_day: int
) -> List[Allocation]:

    allocations = []
    remaining_quota = {exp.id: exp.quota for exp in experiments}

    # 记录每个实验最后一次分配流量的天数
    last_assigned_day = {exp.id: -1 for exp in experiments}

    for day in range(1, max_day + 1):
        # 1. 构建当前可运行的实验的最大堆（按优先级）
        active_exps = []
        for exp in experiments:
            if exp.start_day <= day <= exp.end_day and remaining_quota[exp.id] > 0:
                # 堆存储（-priority, id），模拟最大堆
                heapq.heappush(active_exps, (-exp.priority, exp.id))

        if not active_exps:
            continue

        remaining_capacity = daily_capacity

        # 2. 按优先级分配（每次取堆顶实验）
        while remaining_capacity > 0 and active_exps:
            # 取当前优先级最高的实验
            neg_priority, exp_id = heapq.heappop(active_exps)
            priority = -neg_priority

            # 动态调整优先级：如果超过2天未分配，则临时提高优先级
            if day - last_assigned_day[exp_id] > 2:
                priority += 1  # 防止饿死

            # 分配流量（至少分配1单位）
            assign = min(
                remaining_capacity, remaining_quota[exp_id], max(1, priority * 1000)
            )

            allocations.append(
                Allocation(experiment_id=exp_id, day=day, assigned=assign)
            )
            remaining_quota[exp_id] -= assign
            remaining_capacity -= assign
            last_assigned_day[exp_id] = day

            # 如果实验还有剩余 quota，重新放回堆
            if remaining_quota[exp_id] > 0:
                heapq.heappush(active_exps, (-priority, exp_id))

    return allocations

"""
allocations: 
Day 1: Experiment A assigned 20000
Day 2: Experiment A assigned 20000
Day 3: Experiment A assigned 12000
Day 3: Experiment B assigned 8000
Day 4: Experiment A assigned 12000
Day 4: Experiment B assigned 8000
Day 5: Experiment A assigned 9000
Day 5: Experiment B assigned 6000
Day 5: Experiment C assigned 5000

"""

# =======  task dependency example, A-> B -> C

"""
分成两个问题：
任务编排 -> 依赖关系解决, DAG
资源调度 -> "最优计算节点"的决策维度​


如同交通管制, DAG是红绿灯规则, 调度是实时分配车道、处理救护车优先、绕开拥堵路段。

Bin Packing

"""