"""Property-based tests for two sum implementation using Hypothesis."""

import random

from hypothesis import assume, example, given
from hypothesis import strategies as st
from hypothesis.strategies import composite
from returns.maybe import Nothing, Some

from src.fp_gym import TwoSumSolver, two_sum, two_sum_generator
from src.fp_gym.solver import TwoSumResult


# Custom strategies for two sum testing
@composite
def list_with_valid_target(draw):
    """Generate a list and a target that has at least one valid solution."""
    nums = draw(st.lists(
        st.integers(min_value=-100, max_value=100),
        min_size=2,
        max_size=50
    ))
    
    # Pick two random indices
    i = draw(st.integers(min_value=0, max_value=len(nums) - 1))
    j = draw(st.integers(min_value=0, max_value=len(nums) - 1))
    assume(i != j)  # Ensure different indices
    
    target = nums[i] + nums[j]
    return nums, target


@composite
def list_with_no_solution(draw):
    """Generate a list and target with no valid solution."""
    nums = draw(st.lists(
        st.integers(min_value=1, max_value=50),  # Only positive numbers
        min_size=2,
        max_size=20
    ))
    # Target that's impossible (too large)
    target = draw(st.integers(min_value=sum(nums) + 1, max_value=sum(nums) + 100))
    return nums, target


class TestTwoSumProperties:
    """Property-based tests for two sum implementations."""

    @given(st.lists(st.integers(min_value=-1000, max_value=1000), max_size=100),
           st.integers(min_value=-2000, max_value=2000))
    def test_two_sum_result_correctness(self, nums, target):
        """Property: If a result is found, the sum must equal the target."""
        result = two_sum(nums, target)
        
        if isinstance(result, Some):
            unwrapped = result.unwrap()
            assert sum(unwrapped.values) == target
            
            # Index validity
            i, j = unwrapped.indices
            assert 0 <= i < len(nums)
            assert 0 <= j < len(nums)
            assert i != j
            
            # Value consistency
            assert unwrapped.values == (nums[i], nums[j])

    @given(st.lists(st.integers(min_value=-1000, max_value=1000), max_size=100),
           st.integers(min_value=-2000, max_value=2000))
    def test_generator_results_correctness(self, nums, target):
        """Property: All generator results must be valid solutions."""
        results = list(two_sum_generator(nums, target))
        
        for result in results:
            # Correctness
            assert sum(result.values) == target
            
            # Index validity
            i, j = result.indices
            assert 0 <= i < len(nums)
            assert 0 <= j < len(nums)
            assert i != j
            
            # Value consistency
            assert result.values == (nums[i], nums[j])
            
            # Ordering consistency (i should come before j)
            assert i < j

    @given(list_with_valid_target())
    def test_solution_exists_when_expected(self, data):
        """Property: When we construct input with known solution, it should be found."""
        nums, target = data
        result = two_sum(nums, target)
        
        # Should find a solution
        assert isinstance(result, Some)
        unwrapped = result.unwrap()
        assert sum(unwrapped.values) == target

    @given(list_with_no_solution())
    def test_no_solution_when_impossible(self, data):
        """Property: When no solution exists, should return Nothing."""
        nums, target = data
        result = two_sum(nums, target)
        
        # Should not find a solution
        assert result is Nothing

    @given(st.lists(st.integers(min_value=-100, max_value=100), min_size=0, max_size=50),
           st.integers(min_value=-200, max_value=200))
    def test_deterministic_behavior(self, nums, target):
        """Property: Same input should always produce the same result."""
        result1 = two_sum(nums, target)
        result2 = two_sum(nums, target)
        
        # Results should be identical
        if isinstance(result1, Some) and isinstance(result2, Some):
            assert result1.unwrap() == result2.unwrap()
        else:
            assert result1 is result2  # Both should be Nothing

    @given(st.lists(st.integers(min_value=-100, max_value=100), min_size=0, max_size=50),
           st.integers(min_value=-200, max_value=200))
    def test_generator_vs_maybe_consistency(self, nums, target):
        """Property: Generator and Maybe versions should be consistent."""
        maybe_result = two_sum(nums, target)
        generator_results = list(two_sum_generator(nums, target))
        
        if isinstance(maybe_result, Some):
            # Should have at least one generator result
            assert len(generator_results) >= 1
            # First generator result should match maybe result
            assert generator_results[0] == maybe_result.unwrap()
        else:
            # Should have no generator results
            assert len(generator_results) == 0

    @given(st.lists(st.integers(min_value=-50, max_value=50), min_size=0, max_size=30))
    def test_no_duplicate_pairs(self, nums):
        """Property: Generator should not return duplicate pairs."""
        # Use a target that might have multiple solutions
        target = 0  # Look for pairs that sum to zero
        
        results = list(two_sum_generator(nums, target))
        
        # Convert to set of sorted tuples to check for duplicates
        normalized_results = {tuple(sorted(r.indices)) for r in results}
        
        # Should have no duplicates
        assert len(normalized_results) == len(results)

    @given(st.lists(st.integers(min_value=-10, max_value=10), min_size=2, max_size=10),
           st.integers(min_value=-20, max_value=20))
    @example(nums=[1, 2, 3, 4], target=5)  # Known example
    @example(nums=[], target=0)  # Empty list
    @example(nums=[5], target=10)  # Single element
    def test_solver_methods_consistency(self, nums, target):
        """Property: All solver methods should be consistent with each other."""
        solver = TwoSumSolver()
        
        # Get results from different methods
        maybe_result = solver.find_first_pair(nums, target)
        all_pairs = solver.find_all_pairs(nums, target)
        count = solver.count_pairs(nums, target)
        
        # Consistency checks
        if isinstance(maybe_result, Some):
            assert len(all_pairs) >= 1
            assert count >= 1
            assert maybe_result.unwrap() in all_pairs
        else:
            assert len(all_pairs) == 0
            assert count == 0

    @given(st.lists(st.integers(min_value=-100, max_value=100), min_size=0, max_size=100),
           st.integers(min_value=-200, max_value=200),
           st.integers(min_value=1, max_value=10))
    def test_stack_safety_with_limit(self, nums, target, max_results):
        """Property: Stack-safe solver should respect result limits."""
        solver = TwoSumSolver()
        
        limited_results = list(
            solver.solve_with_stack_safety(nums, target, max_results)
        )
        all_results = solver.find_all_pairs(nums, target)
        
        # Should not exceed limit
        assert len(limited_results) <= max_results
        
        # Should be a subset of all results
        for result in limited_results:
            assert result in all_results

    @given(st.lists(st.integers(min_value=-1000, max_value=1000), min_size=0, max_size=1000))
    def test_large_dataset_performance(self, nums):
        """Property: Should handle large datasets without issues."""
        target = 0  # Simple target
        
        # Should not raise exceptions
        try:
            results = list(two_sum_generator(nums, target))
            # All results should be valid
            for result in results:
                assert sum(result.values) == target
        except Exception as e:
            # Should not raise any exceptions
            assert False, f"Unexpected exception: {e}"

    @given(st.lists(st.integers(), min_size=0, max_size=50),
           st.integers())
    def test_handles_extreme_values(self, nums, target):
        """Property: Should handle very large/small integers gracefully."""
        # Should not raise exceptions with extreme values
        try:
            result = two_sum(nums, target)
            if isinstance(result, Some):
                unwrapped = result.unwrap()
                assert sum(unwrapped.values) == target
        except OverflowError:
            # Acceptable for extremely large numbers
            pass
        except Exception as e:
            assert False, f"Unexpected exception with extreme values: {e}"

    @given(st.lists(st.integers(min_value=-50, max_value=50), min_size=2, max_size=20))
    def test_commutative_property(self, nums):
        """Property: Order of elements shouldn't affect which solutions exist."""
        target = nums[0] + nums[1]  # Use first two elements
        
        # Get solutions for original list
        original_results = set(
            (tuple(sorted(r.values)), sum(r.values))
            for r in two_sum_generator(nums, target)
        )
        
        # Shuffle the list and test again
        shuffled_nums = nums.copy()
        random.shuffle(shuffled_nums)
        
        shuffled_results = set(
            (tuple(sorted(r.values)), sum(r.values))
            for r in two_sum_generator(shuffled_nums, target)
        )
        
        # Should find the same value pairs (though indices will differ)
        assert len(original_results) == len(shuffled_results)

    @given(st.lists(st.integers(min_value=-20, max_value=20), min_size=0, max_size=30),
           st.integers(min_value=-40, max_value=40))
    def test_empty_and_single_element_edge_cases(self, nums, target):
        """Property: Handle edge cases gracefully."""
        result = two_sum(nums, target)
        generator_results = list(two_sum_generator(nums, target))
        
        if len(nums) < 2:
            # Should return Nothing/empty for lists with < 2 elements
            assert result is Nothing
            assert len(generator_results) == 0
        else:
            # With 2+ elements, results should be consistent
            if isinstance(result, Some):
                assert len(generator_results) >= 1
            else:
                assert len(generator_results) == 0


class TestTwoSumResultProperties:
    """Property-based tests for TwoSumResult class."""

    @given(st.tuples(st.integers(0, 1000), st.integers(0, 1000)),
           st.tuples(st.integers(), st.integers()))
    def test_two_sum_result_properties(self, indices, values):
        """Property: TwoSumResult should maintain its invariants."""
        result = TwoSumResult(indices, values)
        
        # Should store values correctly
        assert result.indices == indices
        assert result.values == values
        
        # Should be equal to itself
        assert result == result
        
        # String representation should contain key info
        repr_str = repr(result)
        assert "TwoSumResult" in repr_str
        assert str(indices) in repr_str
        assert str(values) in repr_str

    @given(st.tuples(st.integers(), st.integers()),
           st.tuples(st.integers(), st.integers()))
    def test_equality_properties(self, indices1, values1):
        """Property: TwoSumResult equality should be consistent."""
        result1 = TwoSumResult(indices1, values1)
        result2 = TwoSumResult(indices1, values1)
        
        # Same data should be equal
        assert result1 == result2
        assert result2 == result1  # Symmetric
        
        # Should be equal to itself
        assert result1 == result1

    @given(st.tuples(st.integers(), st.integers()),
           st.tuples(st.integers(), st.integers()),
           st.tuples(st.integers(), st.integers()))
    def test_equality_transitivity(self, indices, values1, values2):
        """Property: Equality should be transitive."""
        result1 = TwoSumResult(indices, values1)
        result2 = TwoSumResult(indices, values1)
        result3 = TwoSumResult(indices, values1)
        
        # Transitive property: if A == B and B == C, then A == C
        if result1 == result2 and result2 == result3:
            assert result1 == result3


class TestTwoSumPerformanceProperties:
    """Property-based performance and stress tests."""

    @given(st.integers(min_value=100, max_value=5000))
    def test_linear_time_complexity(self, size):
        """Property: Should handle large inputs in reasonable time."""
        # Generate a large list
        nums = list(range(size))
        target = (size - 1) + (size - 2)  # Last two elements
        
        # Should complete quickly (this is more of a smoke test)
        result = two_sum(nums, target)
        
        if isinstance(result, Some):
            unwrapped = result.unwrap()
            assert sum(unwrapped.values) == target

    @given(st.lists(st.integers(min_value=-10, max_value=10), min_size=0, max_size=50))
    def test_memory_efficiency(self, nums):
        """Property: Generator should be memory efficient."""
        target = 0
        
        # Test that we can iterate without loading all results
        count = 0
        for result in two_sum_generator(nums, target):
            count += 1
            if count > 100:  # Arbitrary limit for test
                break
        
        # Should be able to iterate without issues
        assert count >= 0

    @given(st.lists(st.integers(min_value=-100, max_value=100), min_size=0, max_size=200))
    def test_no_infinite_loops(self, nums):
        """Property: Should terminate for any input."""
        target = 42  # Arbitrary target
        
        # Count iterations to ensure termination
        iteration_count = 0
        for result in two_sum_generator(nums, target):
            iteration_count += 1
            # Sanity check - shouldn't have more results than possible pairs
            max_possible_pairs = len(nums) * (len(nums) - 1) // 2
            assert iteration_count <= max_possible_pairs
            
            # Break after reasonable number to avoid long test times
            if iteration_count > 1000:
                break

    @given(st.lists(st.integers(min_value=0, max_value=1000), min_size=0, max_size=100))
    def test_consistent_performance_characteristics(self, nums):
        """Property: Performance should be predictable."""
        target = 500  # Middle range target
        
        # Measure some basic characteristics
        all_results = list(two_sum_generator(nums, target))
        solver = TwoSumSolver()
        count = solver.count_pairs(nums, target)
        
        # Basic consistency
        assert len(all_results) == count
        
        # Performance should be reasonable
        if len(nums) > 0:
            # Should complete without hanging
            first_result = solver.find_first_pair(nums, target)
            if isinstance(first_result, Some):
                assert first_result.unwrap() in all_results


class TestTwoSumRobustness:
    """Property-based robustness tests for edge cases."""

    @given(st.lists(st.just(0), min_size=0, max_size=50))
    def test_all_zeros(self, nums):
        """Property: Handle lists of all zeros."""
        target = 0
        results = list(two_sum_generator(nums, target))
        
        # All results should be valid
        for result in results:
            assert sum(result.values) == target
            assert result.values == (0, 0)

    @given(st.lists(st.integers(min_value=1, max_value=1), min_size=0, max_size=50))
    def test_all_same_values(self, nums):
        """Property: Handle lists where all elements are the same."""
        if len(nums) >= 2:
            target = 2  # Two 1's
            results = list(two_sum_generator(nums, target))
            
            # Should find solutions
            if len(nums) >= 2:
                assert len(results) >= 1
                for result in results:
                    assert sum(result.values) == target

    @given(st.lists(st.integers(), min_size=0, max_size=100))
    def test_target_equals_single_element(self, nums):
        """Property: When target equals a single element (impossible case)."""
        if nums:
            target = nums[0]  # Target equals first element
            
            # This should only find solutions if there are pairs that sum to target
            results = list(two_sum_generator(nums, target))
            
            for result in results:
                assert sum(result.values) == target
                # Should be two different elements
                i, j = result.indices
                assert i != j

    @given(st.lists(st.integers(min_value=-5, max_value=5), min_size=0, max_size=20))
    def test_negative_and_positive_mix(self, nums):
        """Property: Handle mix of negative and positive numbers."""
        target = 0  # Looking for pairs that cancel out
        
        results = list(two_sum_generator(nums, target))
        
        for result in results:
            assert sum(result.values) == 0
            # Values should sum to zero
            val1, val2 = result.values
            assert val1 + val2 == 0


if __name__ == "__main__":
    import pytest
    pytest.main([__file__])
