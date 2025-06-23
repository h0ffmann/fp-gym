import munit.FunSuite

import Solution.*

class SortingSuite extends FunSuite {

  test("Sorting must preserve empty or single element lists") {
    val empty = Array[Int]()
    val resultEmpty = QuickSortSolution(empty)

    val single = Array(1)
    val resultSingle = QuickSortSolution(single)

    assertEquals(empty.toList, resultEmpty.toList)
    assertEquals(single.toList, resultSingle.toList)
  }

  test("Sorting basic test") {
    val unsorted = Array(1, 7, 4, 1, 10, 9, -2)
    val sorted = QuickSortSolution(unsorted)

    assertEquals(sorted.toList, List(-2, 1, 1, 4, 7, 9, 10))
  }
}