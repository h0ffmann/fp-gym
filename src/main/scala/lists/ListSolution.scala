package lists

import scala.annotation.tailrec

object ListSolution {

  sealed abstract class RList[+T] {
    def head: T
    def tail: RList[T]
    def isEmpty: Boolean
    def toString: String
    def ::[S >: T](elem: S): RList[S] = new ::(elem, this)
    def apply(index: Int): T
    def lenght: Int
    def reverse: RList[T]

    def ++[S >: T](anotherList: RList[S]): RList[S]
    def removeAt(index: Int): RList[T]
  }

  object RList {
    def fromIter[T](iter: Iterable[T]): RList[T] = {
      @tailrec
      def fromIterLoop(rem: Iterable[T], acc: RList[T]): RList[T] = {
        rem match {
          case x if x.isEmpty => acc
          case z              => fromIterLoop(z.tail, z.head :: acc)
        }
      }
      fromIterLoop(iter, RNil).reverse
    }
  }

  case object RNil extends RList[Nothing] { // nil

    override def head: Nothing = throw new NoSuchElementException
    override def tail: RList[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def toString: String = "[]"

    override def apply(index: Int): Nothing = throw new NoSuchElementException

    override def lenght: Int = 0

    override def reverse: RList[Nothing] = RNil

    override def ++[S >: Nothing](anotherList: RList[S]): RList[S] = anotherList

    override def removeAt(index: Int): RList[Nothing] =
      throw new NoSuchElementException

  }

  case class ::[+T](override val head: T, override val tail: RList[T]) // cons
      extends RList[T] {
    override def isEmpty: Boolean = false
    override def toString: String = {
      @tailrec
      def toStringLoop(rem: RList[T], acc: String): String = {
        rem match {
          case x if x.isEmpty      => acc
          case y if y.tail.isEmpty => s"$acc${y.head}"
          case z                   => toStringLoop(z.tail, s"$acc${z.head}, ")
        }
      }

      "[" + toStringLoop(this, "") + "]"
    }

    override def apply(index: Int): T = { // O(min(N, index))
      @tailrec
      def applyLoop(rem: RList[T], acc: Int): T = {
        rem match {
          case x if x.isEmpty =>
            println("oops 1")
            throw new NoSuchElementException
          case y if acc == index => y.head
          case k if k.tail.isEmpty =>
            println("oops 2")
            throw new NoSuchElementException
          case z => applyLoop(z.tail, acc + 1)
        }
      }
      if (index < 0) throw new NoSuchElementException
      else applyLoop(this, 0)
    }

    override def lenght: Int = { // O(N)
      @tailrec
      def lenghtLoop(rem: RList[T], acc: Int): Int = {
        rem match {
          case x if x.isEmpty => acc
          // case k if k.tail.isEmpty => acc wrong
          case z => lenghtLoop(z.tail, acc + 1)
        }
      }
      lenghtLoop(this, 0)
    }

    override def reverse: RList[T] = { // O(N), prepend is O(1)
      @tailrec
      def reverseLoop(rem: RList[T], acc: RList[T]): RList[T] = { // acc RList
        rem match {
          case x if x.isEmpty => acc
          case z => reverseLoop(z.tail, z.head :: acc) // prepend always
        }
      }
      reverseLoop(this, RNil)
    }

    override def ++[S >: T](anotherList: RList[S]): RList[S] = {
      // O(n1+n2)
      // n1 - first list, n2 - second list
      @tailrec
      def concatenationLoop(rem: RList[S], acc: RList[S]): RList[S] = {
        rem match {
          case x if x.isEmpty => acc
          case z              => concatenationLoop(z.tail, z.head :: acc)
        }
      }

      concatenationLoop(this.reverse, anotherList)
    }

    override def removeAt(index: Int): RList[T] = { // O(N)
      @tailrec
      def removeLoop(
          rem: RList[T],
          accIdx: Int,
          accList: RList[T]
      ): RList[T] = {
        rem match {
          case x if x.isEmpty       => accList.reverse
          case y if accIdx == index => removeLoop(y.tail, accIdx + 1, accList)
          case z => removeLoop(z.tail, accIdx + 1, z.head :: accList)
        }
      }
      if (index < 0) throw new NoSuchElementException
      else removeLoop(this, 0, RNil)
    }

  }

  val anyList: RList[Int] = 1 :: 2 :: 3 :: RNil

}
