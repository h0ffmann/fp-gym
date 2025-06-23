//M2: Parser Configuration

import Solution.*
import scala.annotation.tailrec

case class Config(host: String, port: Int)

sealed trait ConfigError
case object NoHost extends ConfigError
case object NoPort extends ConfigError
case object NoNumericalPort extends ConfigError

object CfgParserSolution extends Solution[Map[String, String], Option[Config]] {

  override def solve(input: Map[String, String]): Option[Config] = {
    // input.get("host").flatMap { host =>
    //   input.get("port").flatMap { port =>
    //     port.toIntOption.map { intPort =>
    //       Config(host, intPort)
    //     }
    //   }
    // }

    for { // syntax suggar for flatMap chains
      host <- input.get("host")
      port <- input.get("port")
      intPort <- port.toIntOption
    } yield Config(host, intPort)
  }


  def solveWithTracking(input: Map[String, String]): Either[ConfigError, Config] = {
    for { 
      host <- input.get("host").toRight(NoHost)
      port <- input.get("port")toRight(NoPort)
      intPort <- port.toIntOption.toRight(NoNumericalPort)
    } yield Config(host, intPort)
  }

  def apply(input: Map[String, String]): Option[Config] = {
    solve(input)
  }
}
