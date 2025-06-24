package lists

import lists.ListSolution.RList
import scala.annotation.tailrec
import lists.ListSolution.RNil

extension [A](rl: RList[A])
  def rle: RList[(A, Int)] = {
    @tailrec
    def rleLoop(
        rem: RList[A],
        last: Option[A],
        acc: RList[(A, Int)]
    ): RList[(A, Int)] = {
      rem match
        case x if x.isEmpty => acc.reverse
        case w if acc.isEmpty =>
          rleLoop(w.tail, Some(w.head), (w.head -> 1) :: RNil) // start
        case z if !last.contains(z.head) =>
          rleLoop(z.tail, Some(z.head), (z.head -> 1) :: acc) // diff element
        case k =>
          rleLoop(
            k.tail,
            Some(k.head),
            (acc.head._1 -> (acc.head._2 + 1)) :: acc.tail
          ) // new element change head
    }
    rleLoop(rl, None, RNil)
  }
