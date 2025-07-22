
"""
Given a string s and an integer k, choosing k adjacent and equal letters from s and removing them, 
causing the left and the right side of the deleted substring to concatenate together.
We repeatedly make k duplicate removals on s until we no longer can.
Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.

Example 1:
Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.

Example 2:
Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"

Example 3:
Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"
"""

def k_duplicate_removal(input_str, k):
    if not input_str or k <= 1:
        return input_str
    
    # Use a stack to keep track of characters
    stack = [] 
    # Use a count stack to keep track of the number of consecutive characters
    count = []
    for char in input_str:
        if stack and stack[-1] == char:
            count[-1] += 1
            if count[-1] == k:
                stack.pop()
                count.pop()
        else:
            stack.append(char)
            count.append(1)

    return ''.join(char * count[i] for i, char in enumerate(stack))


if __name__ == "__main__":
    print(k_duplicate_removal("abcd", 2))
    print(k_duplicate_removal("deeedbbcccbdaa", 3))
    print(k_duplicate_removal("pbbcggttciiippooaais", 2))
