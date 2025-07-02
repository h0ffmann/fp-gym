package lists

import munit.FunSuite
import munit.ScalaCheckSuite
import org.scalacheck.Prop.*
import org.scalacheck.Gen
import scala.util.Random

class FindMissingRepeatTest extends FunSuite {

  test("example 1: [3, 1, 3] should return (3, 2)") {
    val input = List(3, 1, 3)
    val result = input.findMissingRepeat
    assertEquals(result, (3, 2))
  }

  test("example 2: [4, 3, 6, 2, 1, 1] should return (1, 5)") {
    val input = List(4, 3, 6, 2, 1, 1)
    val result = input.findMissingRepeat
    assertEquals(result, (1, 5))
  }

  test("simple case: [2, 2] should return (2, 1)") {
    val input = List(2, 2)
    val result = input.findMissingRepeat
    assertEquals(result, (2, 1))
  }

  test("simple case: [1, 1] should return (1, 2)") {
    val input = List(1, 1)
    val result = input.findMissingRepeat
    assertEquals(result, (1, 2))
  }

  test("three elements: [1, 3, 1] should return (1, 2)") {
    val input = List(1, 3, 1)
    val result = input.findMissingRepeat
    assertEquals(result, (1, 2))
  }

  test("larger case: [1, 2, 3, 4, 6, 7, 8, 8] should return (8, 5)") {
    val input = List(1, 2, 3, 4, 6, 7, 8, 8)
    val result = input.findMissingRepeat
    assertEquals(result, (8, 5))
  }

  test("duplicate at end: [1, 2, 4, 5, 5] should return (5, 3)") {
    val input = List(1, 2, 4, 5, 5)
    val result = input.findMissingRepeat
    assertEquals(result, (5, 3))
  }
}

class FindMissingRepeatPropertyTest extends ScalaCheckSuite {

  // Generator for creating valid test cases
  def validListGen(n: Int): Gen[List[Int]] = {
    require(n >= 2, "n must be at least 2")

    for {
      missingNum <- Gen.choose(1, n)
      duplicateNum <- Gen.choose(1, n).suchThat(_ != missingNum)
    } yield {
      val baseNumbers = (1 to n).filterNot(_ == missingNum).toList
      // Replace one occurrence of duplicateNum to create the duplicate
      val listWithDuplicate = baseNumbers.map(x =>
        if (x == duplicateNum) duplicateNum else x
      ) :+ duplicateNum
      // Simple shuffle using scala.util.Random
      Random.shuffle(listWithDuplicate)
    }
  }

  property(
    "findMissingRepeat correctly identifies duplicate and missing for size 2-10"
  ) {
    forAll(Gen.choose(2, 10).flatMap(validListGen)) { list =>
      val (duplicate, missing) = list.findMissingRepeat

      // Verify duplicate appears exactly twice in the list
      val duplicateCount = list.count(_ == duplicate)

      // Verify missing is not in the list
      val missingInList = list.contains(missing)

      // Verify missing is in valid range
      val n = list.length
      val missingInRange = missing >= 1 && missing <= n

      // Verify duplicate is in valid range
      val duplicateInRange = duplicate >= 1 && duplicate <= n

      // Verify we have exactly n-1 unique numbers (since one is missing)
      val uniqueCount = list.toSet.size

      duplicateCount == 2 &&
      !missingInList &&
      missingInRange &&
      duplicateInRange &&
      uniqueCount == n - 1
    }
  }

  property("all numbers except missing should be in range 1 to n") {
    forAll(Gen.choose(3, 8).flatMap(validListGen)) { list =>
      val n = list.length
      val (_, missing) = list.findMissingRepeat
      val numbersExceptMissing = list.toSet

      numbersExceptMissing.forall(x => x >= 1 && x <= n) &&
      !numbersExceptMissing.contains(missing)
    }
  }

  property("sum property: actualSum = expectedSum - missing + duplicate") {
    forAll(Gen.choose(2, 10).flatMap(validListGen)) { list =>
      val n = list.length
      val (duplicate, missing) = list.findMissingRepeat

      val actualSum = list.sum
      val expectedSum = n * (n + 1) / 2

      actualSum == expectedSum - missing + duplicate
    }
  }

  // Test with manually created edge cases
  property("handles edge case of n=2") {
    val testCases = List(
      (List(1, 1), (1, 2)),
      (List(2, 2), (2, 1))
    )

    testCases.forall { case (input, expected) =>
      input.findMissingRepeat == expected
    }
  }
}

// Additional stress test
class FindMissingRepeatStressTest extends FunSuite {

  test("stress test with larger inputs (n=100)") {
    // Create a list of 1 to 100, remove 50, duplicate 25
    val baseList = (1 to 100).filterNot(_ == 50).toList
    val withDuplicate = baseList :+ 25
    val shuffled = Random.shuffle(withDuplicate)

    val result = shuffled.findMissingRepeat
    assertEquals(result, (25, 50))
  }

  test("stress test: verify performance with larger input") {
    val n = 1000
    val missing = 500
    val duplicate = 250

    val baseList = (1 to n).filterNot(_ == missing).toList
    val withDuplicate = baseList :+ duplicate
    val shuffled = Random.shuffle(withDuplicate)

    val start = System.nanoTime()
    val result = shuffled.findMissingRepeat
    val duration = System.nanoTime() - start

    assertEquals(result, (duplicate, missing))
    // Should complete in reasonable time (less than 10ms for 1000 elements)
    assert(duration < 10_000_000, s"Too slow: ${duration}ns")
  }
}
