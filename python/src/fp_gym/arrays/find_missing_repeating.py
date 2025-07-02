"""
Missing and Repeating in an Array
Given an unsorted array of size n. Array elements are in the range of 1 to n.
One number from set {1, 2, ...n} is missing and one number occurs twice in the array.
The task is to find these two numbers.

Examples:

Input: arr[] = {3, 1, 3}
Output: 3, 2
Explanation: In the array, 2 is missing and 3 occurs twice.

Input: arr[] = {4, 3, 6, 2, 1, 1}
Output: 1, 5
Explanation: 5 is missing and 1 is repeating.
"""


def find_missing_repeating(arr: list[int]) -> tuple[int, int]:
    seen = set()
    duplicate = -1

    for num in arr:
        if num in seen:
            duplicate = num
        else:
            seen.add(num)

    n = len(arr)
    expected = set(range(1, n + 1))
    missing = list(expected - seen)[0]

    return (missing, duplicate)


def find_missing_repeating_optimal(arr: list[int]) -> tuple[int, int]:
    n: int = len(arr)
    repeating_element: int = -1

    for i in range(n):
        element_value: int = abs(arr[i])  # Get original value (ignore sign)
        target_index: int = element_value - 1  # Convert 1-based to 0-based index

        if arr[target_index] > 0:
            # First time seeing this element - mark as visited
            arr[target_index] = -arr[target_index]
        else:
            # Already negative means we've seen this element before
            # This is our repeating element
            repeating_element = element_value

    missing_element: int = -1
    for i in range(n):
        if arr[i] > 0:
            # Positive value means index i+1 was never visited
            missing_element = i + 1  # Convert back to 1-based
            break

    return (missing_element, repeating_element)
