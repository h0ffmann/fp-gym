package common

object Timer {
  import scala.util.Random

  def timeIt[T](operation: => T): (T, Long) = {
    val start = System.nanoTime()
    val result = operation
    val end = System.nanoTime()
    (result, (end - start) / 1000000) // Convert to milliseconds
  }

}
