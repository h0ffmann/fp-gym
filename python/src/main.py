#!/usr/bin/env python3
"""
Main application demonstrating the Two Sum implementation.

This script showcases the generator-based, stack-safe two sum solver
with a simple example case.
"""


from returns.maybe import Nothing, Some

from fp_gym import TwoSumSolver, two_sum


def main() -> None:
    """Main application entry point."""
    print("🧮 Two Sum Problem Solver")
    print("=" * 50)
    print()

    # Example case
    nums: list[int] = [2, 7, 11, 15, 3, 6]
    target: int = 9

    print(f"📊 Input array: {nums}")
    print(f"🎯 Target sum: {target}")
    print()

    # Create solver instance
    solver = TwoSumSolver()

    # Demonstrate different solving approaches
    print("🔍 Finding first pair using Maybe monad:")
    result = two_sum(nums, target)

    if isinstance(result, Some):
        unwrapped = result.unwrap()
        print(f"   ✅ Found pair at indices {unwrapped.indices}")
        print(f"   📝 Values: {unwrapped.values}")
        print(f"   ➕ Sum: {sum(unwrapped.values)}")
    elif isinstance(result, Nothing):
        print("   ❌ No solution found")

    print()

    # Find all pairs
    print("🔍 Finding all pairs using generator:")
    all_pairs = solver.find_all_pairs(nums, target)

    if all_pairs:
        for i, pair in enumerate(all_pairs):
            print(f"   Pair {i + 1}: indices {pair.indices}, values {pair.values}")
    else:
        print("   ❌ No pairs found")

    print()

    # Count pairs
    pair_count = solver.count_pairs(nums, target)
    print(f"📊 Total pairs found: {pair_count}")

    print()

    # Demonstrate stack safety with larger dataset
    print("🚀 Demonstrating stack safety with larger dataset:")
    large_nums = list(range(1000))
    large_target = 1999

    print(f"   📊 Array size: {len(large_nums)}")
    print(f"   🎯 Target: {large_target}")

    # Use stack-safe solver with limit
    stack_safe_results = list(
        solver.solve_with_stack_safety(large_nums, large_target, max_results=1)
    )

    if stack_safe_results:
        result = stack_safe_results[0]
        print(f"   ✅ Found pair: indices {result.indices}, values {result.values}")
        print(f"   🧮 Sum verification: {sum(result.values)} == {large_target}")
    else:
        print("   ❌ No solution found in large dataset")

    print()
    print("🎉 Two Sum demonstration completed!")
    print("💡 This implementation uses generators for memory efficiency")
    print("🛡️  and stack safety, making it suitable for large datasets.")


if __name__ == "__main__":
    main()
