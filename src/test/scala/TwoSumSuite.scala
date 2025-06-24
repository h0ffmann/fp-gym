import munit.FunSuite

import common.Solution
import common.Solution.*

class TwoSumSuite extends FunSuite {

  test(
    "TwoSumSolution should return the correct indices for a valid solution"
  ) {
    val nums = Array(2, 7, 11, 15)
    val target = 9

    val result = TwoSumSolution(nums, target)
    val resultMut = TwoSumMutSolution(nums, target)
    val expected = Array(0, 1)
    val errorMsg = "The resulting indices did not match the expected ones."

    assertEquals(result.toList, expected.toList, errorMsg)
    assertEquals(resultMut.toList, expected.toList, errorMsg)
  }

  test(
    "findTwoSum should return Left(NoPossibleSolution) when no pair sums to the target"
  ) {
    val nums = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    val target = 45

    val result =
      TwoSumSolution.findTwoSum(nums.zipWithIndex.toList, Map.empty, target)
    val expected = Left(NoPossibleSolution)

    assertEquals(
      result,
      expected,
      "The function should have returned a NoPossibleSolution error."
    )
  }

  test("TwoSumSolution should handle an empty input array") {
    val nums = Array.empty[Int]
    val target = 10
    intercept[NoSuchElementException] {
      TwoSumSolution(nums, target)
    }
    intercept[NoSuchElementException] {
      TwoSumMutSolution(nums, target)
    }
  }
}
