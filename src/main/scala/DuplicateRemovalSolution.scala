//E3: Deduplication from list

import Solution.*
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

class DuplicateRemovalSolution[T] extends Solution[List[T], List[T]] {

  override def solve(input: List[T]): List[T] = {
    // input.foldLeft((List.empty[T], Set.empty[T])){
    //     case ((acc, seen), cur) =>
    //         if (seen.contains(cur)){ // O(1)
    //             (acc, seen)
    //         } else {
    //             (acc :+ cur, seen + cur) // O(n) APPEND
    //         }
    // }._1
    input
      .foldLeft((List.empty[T], Set.empty[T])) { case ((acc, seen), cur) =>
        if (seen.contains(cur)) { // O(1)
          (acc, seen)
        } else {
          (cur :: acc, seen + cur) // O(1) PREPEND + reverse
        }
      }
      ._1
      .reverse
  }

  def dummy(input: List[T]): List[T] = {
    input.distinct
  }

  def optimal_solve(input: List[T]): List[T] = {
    val seen = scala.collection.mutable.Set.empty[T]
    val result = ListBuffer.empty[T]

    @tailrec
    def loop(remaining: List[T]): Unit = {
      remaining match {
        case Nil => ()
        case head :: tail =>
          if (!seen.contains(head)) {
            seen += head
            result += head
          }
          loop(tail)
      }
    }

    loop(input)
    result.toList
  }

  def apply(input: List[T]): List[T] = {
    solve(input)
  }
}
