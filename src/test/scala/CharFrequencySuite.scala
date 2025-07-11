import munit.FunSuite

import common.Solution

class CharFrequencySuite extends FunSuite {

  test("CharFrequency basic test") {
    val str1 = "hello world"
    val exp1 = Map(
      'h' -> 1,
      'e' -> 1,
      'l' -> 3,
      'o' -> 2,
      ' ' -> 1,
      'w' -> 1,
      'r' -> 1,
      'd' -> 1
    )
    val res1 = CharFrequencySolution(str1)

    val str2 = "Scala is fun!"
    val exp2 = Map(
      'S' -> 1,
      'c' -> 1,
      'a' -> 2,
      'l' -> 1,
      ' ' -> 2,
      'i' -> 1,
      's' -> 1,
      'f' -> 1,
      'u' -> 1,
      'n' -> 1,
      '!' -> 1
    )
    val res2 = CharFrequencySolution(str2)

    val str3 = ""
    val exp3 = Map.empty[Char, Int]
    val res3 = CharFrequencySolution(str3)

    val str4 = "aaaaa"
    val exp4 = Map('a' -> 5)
    val res4 = CharFrequencySolution(str4)

    assertEquals(res1, exp1)
    assertEquals(res2, exp2)
    assertEquals(res3, exp3)
    assertEquals(res4, exp4)
  }

  test("AnagramChecker basic test") {
    val str1 = "listen"
    val str2 = "silent"
    val result1 = AnagramCheckerSolution(str1, str2)

    val str3 = "Debit Card"
    val str4 = "Bad Credit"
    val result2 = AnagramCheckerSolution(str3, str4)

    val str5 = "hello"
    val str6 = "world"
    val result3 = AnagramCheckerSolution(str5, str6)

    val str7 = "rail safety"
    val str8 = "fairy tales"
    val result4 = AnagramCheckerSolution(str7, str8)

    assertEquals(result1, true)
    assertEquals(result2, true)
    assertEquals(result3, false)
    assertEquals(result4, true)
  }
}
