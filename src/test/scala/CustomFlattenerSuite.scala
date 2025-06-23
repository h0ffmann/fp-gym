import munit.FunSuite

import Solution.*

class CustomFlattenerSuite extends FunSuite {
  test("CustomFlattener basic test with Int") {
    val intFlattener = new CustomFlattenerSolution[Int]
    assertEquals(intFlattener(List(List(1, 2), List(3, 4), List(5))), List(1, 2, 3, 4, 5)) 
    assertEquals(intFlattener(List()), List()) 
    assertEquals(intFlattener(List(List(1), List(2), List(3))), List(1, 2, 3))
  }
  
  test("CustomFlattener basic test with String") {
    val stringFlattener = new CustomFlattenerSolution[String]
    assertEquals(stringFlattener(List(List("a", "b"), List(), List("c"))), List("a", "b", "c")) 
  }
}