package lists

import lists.ListSolution.RList
import scala.annotation.tailrec
import java.text.Bidi
import lists.ListSolution.RNil

extension [A](rl: RList[A]) // O(n*f)
  def map[B](f: A => B): RList[B] = {
    @tailrec
    def mapLoop(rem: RList[A], acc: RList[B]): RList[B] = {
      rem match
        case x if x.isEmpty => acc.reverse
        case z              => mapLoop(rem.tail, f(rem.head) :: acc)
    }
    mapLoop(rl, RNil)
  }

extension [A](rl: RList[A]) 
  def flatMap[B](f: A => RList[B]): RList[B] = {
    @tailrec
    def fMapLoop(rem: RList[A], acc: RList[B]): RList[B] = {
      rem match
        case x if x.isEmpty => acc.reverse
        case z              => fMapLoop(rem.tail, f(rem.head).reverse ++ acc)
    }
    fMapLoop(rl, RNil)
  }

extension [A](rl: RList[A])
  def filter(f: A => Boolean): RList[A] = {
    @tailrec
    def filterLoop(rem: RList[A], acc: RList[A]): RList[A] = {
      rem match
        case x if x.isEmpty => acc.reverse
        case z if f(z.head) => filterLoop(z.tail, z.head :: acc)
        case _              => filterLoop(rem.tail, acc)
    }
    filterLoop(rl, RNil)
  }
