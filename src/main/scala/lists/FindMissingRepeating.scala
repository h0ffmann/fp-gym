package lists
/*
Missing and Repeating in an Array
Given an unsorted array of size n. Array elements are in the range of 1 to n. One number from set {1, 2, ...n} is missing and one number occurs twice in the array. The task is to find these two numbers.

Examples:

Input: arr[] = {3, 1, 3}
Output: 3, 2
Explanation: In the array, 2 is missing and 3 occurs twice.

Input: arr[] = {4, 3, 6, 2, 1, 1}
Output: 1, 5
Explanation: 5 is missing and 1 is repeating.
 */

import lists.ListSolution.RList
import scala.annotation.tailrec
import lists.ListSolution.RNil
import scala.collection.mutable.{Set => MutSet}
import scala.collection.immutable.Set

extension (rl: List[Int])
  def findMissingRepeat: (Int, Int) = {
    @tailrec
    def helper(
        rem: List[Int],
        duplicate: Option[Int],
        seen: Set[Int]
    ): (Option[Int], Set[Int]) = {
      rem match
        case Nil => (duplicate, seen)
        case h :: t if seen.contains(h) =>
          helper(t, Some(h), seen)
        case h :: t =>
          helper(t, duplicate, seen + h)
    }

    val (duplicateOpt, seenNumbers) = helper(rl, None, Set.empty)
    val duplicate = duplicateOpt.getOrElse(-1)

    // Since one number is missing from 1 to n, seenNumbers.size = n-1
    // So n = seenNumbers.size + 1
    val n = seenNumbers.size + 1
    val expected = (1 to n).toSet
    val missing = (expected -- seenNumbers).headOption.getOrElse(-1)

    // - 1 no value found for duplicate or missing
    (duplicate, missing)
  }
