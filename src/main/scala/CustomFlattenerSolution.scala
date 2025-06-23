//E2: List Flattener

import Solution.*

class CustomFlattenerSolution[T] extends Solution[List[List[T]], List[T]] {

  override def solve(input: List[List[T]]): List[T] = {
    //input.flatMap(identity)
    input.foldLeft(List.empty[T]){
        case (acc, cur) => acc ::: cur
    }
  }
  

  def apply(input: List[List[T]]): List[T] = {
    solve(input)
  }
}
