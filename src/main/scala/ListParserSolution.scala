// 11. You have val data: List. Write a function to parse these strings into integers, ignoring any that are not valid numbers, and then sum the resulting integers. Do this in a single, functional expression.

import common.Solution
import scala.util.Try
import scala.util.Success
import scala.util.Failure

object ListParserSolution extends Solution[List[String], Int] {

  override def solve(data: List[String]): Int = {
    data.map(_.toIntOption).flatMap(_.toList).reduceOption(_ + _).getOrElse(0)
  }

  def solveEither(data: List[String]): Int = {
    data
      .map { x =>
        Try(x.toInt) match {
          case Success(value)     => Right(value)
          case Failure(exception) => Left("error")
        }
      }
      .collect { case Right(value) => value }
      .sum
  }

  def apply(input: List[String]): Int = {
    solve(input)
  }
}
