//E3: Deduplication from list

import Solution.*

class DuplicateRemovalSolution[T] extends Solution[List[T], List[T]] {

  override def solve(input: List[T]): List[T] = {
    input.foldLeft((List.empty[T], Set.empty[T])){
        case ((acc, seen), cur) =>
            if (seen.contains(cur)){ // O(1)
                (acc, seen)
            } else {
                (acc :+ cur, seen + cur)
            }
    }._1
  }
  

  def apply(input: List[T]): List[T] = {
    solve(input)
  }
}
