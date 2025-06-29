"""Tests for the two sum implementation."""

import pytest
from returns.maybe import Maybe, Nothing, Some

from src.fp_gym import TwoSumSolver, two_sum, two_sum_generator
from src.fp_gym.solver import TwoSumResult


class TestTwoSumGenerator:
    """Test cases for the generator-based two sum implementation."""

    def test_basic_two_sum_found(self) -> None:
        """Test basic case where solution exists."""
        nums = [2, 7, 11, 15]
        target = 9
        results = list(two_sum_generator(nums, target))

        assert len(results) == 1
        result = results[0]
        assert result.indices == (0, 1)
        assert result.values == (2, 7)
        assert sum(result.values) == target

    def test_two_sum_not_found(self) -> None:
        """Test case where no solution exists."""
        nums = [1, 2, 3, 4]
        target = 10
        results = list(two_sum_generator(nums, target))

        assert len(results) == 0

    def test_multiple_solutions(self) -> None:
        """Test case with multiple valid pairs."""
        nums = [1, 2, 3, 2, 4]
        target = 4
        results = list(two_sum_generator(nums, target))

        # Should find (0,4) -> (1,3) and (1,3) -> (2,2)
        assert len(results) >= 1

        # Verify all results are valid
        for result in results:
            assert sum(result.values) == target
            i, j = result.indices
            assert nums[i] + nums[j] == target

    def test_empty_list(self) -> None:
        """Test with empty input list."""
        nums: list[int] = []
        target = 5
        results = list(two_sum_generator(nums, target))

        assert len(results) == 0

    def test_single_element(self) -> None:
        """Test with single element list."""
        nums = [5]
        target = 10
        results = list(two_sum_generator(nums, target))

        assert len(results) == 0

    def test_negative_numbers(self) -> None:
        """Test with negative numbers."""
        nums = [-1, -2, -3, 5]
        target = 2
        results = list(two_sum_generator(nums, target))

        assert len(results) == 1
        result = results[0]
        assert sum(result.values) == target

    def test_large_dataset_stack_safety(self) -> None:
        """Test stack safety with large dataset."""
        # Create a large dataset
        nums = list(range(10000))
        target = 19997  # 9998 + 9999 = 19997 (achievable)

        results = list(two_sum_generator(nums, target))

        assert len(results) == 1
        result = results[0]
        assert result.indices == (9998, 9999)
        assert result.values == (9998, 9999)
        assert sum(result.values) == target


class TestTwoSumMaybe:
    """Test cases for the Maybe-based two sum implementation."""

    def test_two_sum_found_returns_some(self) -> None:
        """Test that finding a solution returns Some."""
        nums = [2, 7, 11, 15]
        target = 9
        result = two_sum(nums, target)

        assert isinstance(result, Some)
        unwrapped = result.unwrap()
        assert unwrapped.indices == (0, 1)
        assert unwrapped.values == (2, 7)

    def test_two_sum_not_found_returns_nothing(self) -> None:
        """Test that no solution returns Nothing."""
        nums = [1, 2, 3, 4]
        target = 10
        result = two_sum(nums, target)

        # Fix: Use 'is Nothing' instead of isinstance
        assert result is Nothing

    def test_two_sum_empty_list_returns_nothing(self) -> None:
        """Test that empty list returns Nothing."""
        nums: list[int] = []
        target = 5
        result = two_sum(nums, target)

        # Fix: Use 'is Nothing' instead of isinstance
        assert result is Nothing


class TestTwoSumSolver:
    """Test cases for the TwoSumSolver class."""

    def setup_method(self) -> None:
        """Set up test fixtures."""
        self.solver = TwoSumSolver()

    def test_find_all_pairs(self) -> None:
        """Test finding all pairs."""
        nums = [1, 2, 3, 2, 4]
        target = 4
        results = self.solver.find_all_pairs(nums, target)

        assert len(results) >= 1
        for result in results:
            assert sum(result.values) == target

    def test_find_first_pair_found(self) -> None:
        """Test finding first pair when solution exists."""
        nums = [2, 7, 11, 15]
        target = 9
        result = self.solver.find_first_pair(nums, target)

        assert isinstance(result, Some)
        unwrapped = result.unwrap()
        assert unwrapped.indices == (0, 1)

    def test_find_first_pair_not_found(self) -> None:
        """Test finding first pair when no solution exists."""
        nums = [1, 2, 3, 4]
        target = 10
        result = self.solver.find_first_pair(nums, target)

        # Fix: Use 'is Nothing' instead of isinstance
        assert result is Nothing

    def test_count_pairs(self) -> None:
        """Test counting pairs."""
        nums = [1, 2, 3, 2, 1]
        target = 3
        count = self.solver.count_pairs(nums, target)

        assert count >= 1

    def test_count_pairs_no_solution(self) -> None:
        """Test counting pairs when no solution exists."""
        nums = [1, 2, 3, 4]
        target = 10
        count = self.solver.count_pairs(nums, target)

        assert count == 0

    def test_solve_with_stack_safety_unlimited(self) -> None:
        """Test stack-safe solver without limit."""
        nums = [1, 2, 3, 2, 1]
        target = 3
        results = list(self.solver.solve_with_stack_safety(nums, target))

        assert len(results) >= 1
        for result in results:
            assert sum(result.values) == target

    def test_solve_with_stack_safety_limited(self) -> None:
        """Test stack-safe solver with result limit."""
        nums = [1, 2, 3, 2, 1, 2]
        target = 3
        max_results = 2
        results = list(
            self.solver.solve_with_stack_safety(nums, target, max_results)
        )

        assert len(results) <= max_results
        for result in results:
            assert sum(result.values) == target

    def test_solve_with_stack_safety_large_dataset(self) -> None:
        """Test stack safety with large dataset."""
        # Create a dataset that would cause stack overflow with naive recursion
        nums = list(range(1000))
        target = 1997  # 998 + 999 = 1997 (achievable)

        results = list(self.solver.solve_with_stack_safety(nums, target, 1))

        assert len(results) == 1
        result = results[0]
        assert result.indices == (998, 999)
        assert result.values == (998, 999)
        assert sum(result.values) == target


class TestTwoSumResult:
    """Test cases for TwoSumResult class."""

    def test_two_sum_result_creation(self) -> None:
        """Test creating a TwoSumResult."""
        result = TwoSumResult((0, 1), (2, 7))

        assert result.indices == (0, 1)
        assert result.values == (2, 7)

    def test_two_sum_result_equality(self) -> None:
        """Test TwoSumResult equality."""
        result1 = TwoSumResult((0, 1), (2, 7))
        result2 = TwoSumResult((0, 1), (2, 7))
        result3 = TwoSumResult((1, 2), (3, 4))

        assert result1 == result2
        assert result1 != result3

    def test_two_sum_result_repr(self) -> None:
        """Test TwoSumResult string representation."""
        result = TwoSumResult((0, 1), (2, 7))
        repr_str = repr(result)

        assert "TwoSumResult" in repr_str
        assert "(0, 1)" in repr_str
        assert "(2, 7)" in repr_str


if __name__ == "__main__":
    pytest.main([__file__])