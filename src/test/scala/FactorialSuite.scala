import munit.FunSuite

import Solution.*

class FactorialSuite extends FunSuite {

  test("Factorial basic test") {

    assertEquals(FactorialSolution(0), BigInt(1))
    assertEquals(FactorialSolution(1), BigInt(1))
    assertEquals(FactorialSolution(5), BigInt(120))
    assertEquals(FactorialSolution(20), BigInt("2432902008176640000"))
    assert(FactorialSolution(1000).toString.length > 2000)
  }

}