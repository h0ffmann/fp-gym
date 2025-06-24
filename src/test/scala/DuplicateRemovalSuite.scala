import munit.FunSuite

import common.Solution

class DuplicateRemovalSuite extends FunSuite {

  test("Dedup basic test with Int") {
    val intDedup = new DuplicateRemovalSolution[Int]
    assertEquals(intDedup(List(1, 2, 3, 2, 4, 1, 5)), List(1, 2, 3, 4, 5))
    assertEquals(intDedup(List()), List())
    assertEquals(intDedup(List(1, 2, 3, 4, 5)), List(1, 2, 3, 4, 5))
  }

  test("Dedup basic test with String") {
    val stringDedup = new DuplicateRemovalSolution[String]
    assertEquals(
      stringDedup(List("a", "b", "a", "c", "b")),
      List("a", "b", "c")
    )
    assertEquals(stringDedup(List()), List())
  }
}
