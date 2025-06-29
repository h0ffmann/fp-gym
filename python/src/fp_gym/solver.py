"""
Two Sum implementation using generators for stack safety and memory efficiency.

This module provides a stack-safe implementation of the two sum problem using
generators to avoid stack overflow on large datasets and enable lazy evaluation.
"""

from collections.abc import Generator

from returns.maybe import Maybe, Nothing, Some


class TwoSumResult:
    """Container for two sum results with lazy evaluation."""

    def __init__(self, indices: tuple[int, int], values: tuple[int, int]) -> None:
        self.indices = indices
        self.values = values

    def __repr__(self) -> str:
        return f"TwoSumResult(indices={self.indices}, values={self.values})"

    def __eq__(self, other: object) -> bool:
        if not isinstance(other, TwoSumResult):
            return False
        return self.indices == other.indices and self.values == other.values


def two_sum_generator(nums: list[int], target: int) -> Generator[TwoSumResult]:
    """
    Generator-based two sum implementation for stack safety.
    
    Yields all possible pairs that sum to the target value.
    Uses generators to maintain stack safety even with large datasets.
    
    Args:
        nums: List of integers to search
        target: Target sum value
        
    Yields:
        TwoSumResult: Contains indices and values of pairs that sum to target
        
    Example:
        >>> nums = [2, 7, 11, 15]
        >>> target = 9
        >>> results = list(two_sum_generator(nums, target))
        >>> print(results[0].indices)  # (0, 1)
        >>> print(results[0].values)   # (2, 7)
    """
    seen: dict[int, int] = {}

    for i, num in enumerate(nums):
        complement = target - num

        if complement in seen:
            # Found a pair - yield the result
            j = seen[complement]
            result = TwoSumResult(
                indices=(j, i),
                values=(complement, num)
            )
            yield result

        seen[num] = i


def two_sum(nums: list[int], target: int) -> Maybe[TwoSumResult]:
    """
    Find the first pair of numbers that sum to target using Maybe monad.
    
    Args:
        nums: List of integers to search
        target: Target sum value
        
    Returns:
        Maybe[TwoSumResult]: Some(result) if found, Nothing if not found
        
    Example:
        >>> nums = [2, 7, 11, 15]
        >>> result = two_sum(nums, 9)
        >>> if isinstance(result, Some):
        ...     print(f"Found at indices: {result.unwrap().indices}")
    """
    try:
        # Get the first result from the generator
        first_result = next(two_sum_generator(nums, target))
        return Some(first_result)
    except StopIteration:
        return Nothing


class TwoSumSolver:
    """
    Stack-safe two sum solver using generators and functional programming patterns.
    
    This class provides multiple methods for solving the two sum problem,
    all using generator-based approaches for memory efficiency and stack safety.
    """

    def __init__(self) -> None:
        """Initialize the solver."""
        pass

    def find_all_pairs(self, nums: list[int], target: int) -> list[TwoSumResult]:
        """
        Find all pairs that sum to the target.
        
        Args:
            nums: List of integers to search
            target: Target sum value
            
        Returns:
            List of all TwoSumResult objects found
        """
        return list(two_sum_generator(nums, target))

    def find_first_pair(self, nums: list[int], target: int) -> Maybe[TwoSumResult]:
        """
        Find the first pair that sums to the target.
        
        Args:
            nums: List of integers to search
            target: Target sum value
            
        Returns:
            Maybe[TwoSumResult]: Some(result) if found, Nothing if not found
        """
        return two_sum(nums, target)

    def count_pairs(self, nums: list[int], target: int) -> int:
        """
        Count the number of pairs that sum to the target.
        
        Uses generator to avoid loading all results into memory.
        
        Args:
            nums: List of integers to search
            target: Target sum value
            
        Returns:
            Number of pairs found
        """
        count = 0
        for _ in two_sum_generator(nums, target):
            count += 1
        return count

    def solve_with_stack_safety(
        self,
        nums: list[int],
        target: int,
        max_results: int | None = None
    ) -> Generator[TwoSumResult]:
        """
        Stack-safe solver that yields results lazily.
        
        Args:
            nums: List of integers to search
            target: Target sum value
            max_results: Maximum number of results to return (optional)
            
        Yields:
            TwoSumResult: Results as they are found
        """
        count = 0
        for result in two_sum_generator(nums, target):
            if max_results is not None and count >= max_results:
                break
            yield result
            count += 1
