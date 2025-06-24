import munit.FunSuite
import lists.ListSolution._
import lists.rle // Import the extension method

class RLESuite extends FunSuite {

  test("RLE should handle empty list") {
    val empty: RList[Int] = RNil
    val result = empty.rle
    val expected: RList[(Int, Int)] = RNil

    assertEquals(result.toString, expected.toString)
  }

  test("RLE should handle single element") {
    val single = 5 :: RNil
    val result = single.rle
    val expected = (5, 1) :: RNil

    assertEquals(result.toString, expected.toString)
  }

  test("RLE should handle consecutive identical elements") {
    val list = 1 :: 1 :: 1 :: RNil
    val result = list.rle
    val expected = (1, 3) :: RNil

    assertEquals(result.toString, expected.toString)
  }

  test("RLE should handle different consecutive elements") {
    val list = 1 :: 2 :: 3 :: RNil
    val result = list.rle
    val expected = (1, 1) :: (2, 1) :: (3, 1) :: RNil

    assertEquals(result.toString, expected.toString)
  }

  test("RLE should handle mixed consecutive runs") {
    val list = 1 :: 1 :: 2 :: 2 :: 3 :: RNil
    val result = list.rle
    val expected = (1, 2) :: (2, 2) :: (3, 1) :: RNil

    assertEquals(result.toString, expected.toString)
  }

  test("RLE should handle non-consecutive duplicates - CRITICAL TEST") {
    val list = 1 :: 1 :: 2 :: 2 :: 1 :: 1 :: RNil
    val result = list.rle
    val expected = (1, 2) :: (2, 2) :: (1, 2) :: RNil

    assertEquals(
      result.toString,
      expected.toString,
      "RLE should create separate runs for non-consecutive identical elements"
    )
  }

  test("RLE should handle alternating elements") {
    val list = 1 :: 2 :: 1 :: 2 :: RNil
    val result = list.rle
    val expected = (1, 1) :: (2, 1) :: (1, 1) :: (2, 1) :: RNil

    assertEquals(result.toString, expected.toString)
  }

  test("RLE should handle all same elements") {
    val list = 7 :: 7 :: 7 :: 7 :: 7 :: RNil
    val result = list.rle
    val expected = (7, 5) :: RNil

    assertEquals(result.toString, expected.toString)
  }

  test("RLE should work with String elements") {
    val list = "a" :: "a" :: "b" :: "b" :: "b" :: "a" :: RNil
    val result = list.rle
    val expected = ("a", 2) :: ("b", 3) :: ("a", 1) :: RNil

    assertEquals(result.toString, expected.toString)
  }
}
