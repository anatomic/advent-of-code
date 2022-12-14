package year2022

import scala.io.Source

object Day14 {

  private val input = Source.fromResource("2022/14.test.txt")
  opaque type Point = (Int, Int)

  def main(args: Array[String]): Unit =
    part1

  def parseInput =
    input
      .getLines()
      .map(
        _.split(" -> ")
        .map(_.split(",").map(_.toInt))
      )

  
}
