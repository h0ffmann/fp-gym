import Solution.*

object Main extends App {

  val myString = "This is my string"
  val res = myString.groupBy(identity(_)).map(x => x._1 -> x._2.length())
  println(res)
}