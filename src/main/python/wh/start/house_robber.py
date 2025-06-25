

"""
Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.

"""
# f[i] = max(num[i]+f[i-2], f[i11])
# https://leetcode.com/problems/house-robber/description/

class Solution:
    def rob(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return 0
        if len(nums) == 1:
            return nums[0]
        if len(nums) == 2:
            return max(nums[0], nums[1])

        length = len(nums)
        i = 2

        # status = [nums[0], max(nums[0], nums[1])] #[2, 7, 11, 11, 12, 15]
        status_pre2 = max(nums[0])
        status_pre1 = max(nums[1])
        status_cur = 0

        while i<length:
            # status.append(max(num[i] + status[i-2], status[i-1]))
            status_cur = max(status_pre2 + num[i], status_pre1)
            status_pre2 = status_pre1
            status_pre1 = status_cur
            i = i+1
        
        return max(status)


    def rob_rc(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return 0
        if len(nums) == 1:
            return nums[0]
        if len(nums) == 2:
            return max(nums[0], nums[1])

        return max(rob_rc(nums[0:i-1]), rob_rc(nums[0:i-2]) + nums[i])


        
