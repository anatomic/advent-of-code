package year2022

import scala.io.Source

object Day14 {

  private val input = Source.fromResource("2022/14.test.txt")
  opaque type Point = (Int, Int)

  def main(args: Array[String]): Unit =
    println(parseInput)

  def parseInput =
    input
      .getLines()
      .map(
        _.split(" -> ")
          .map(toPoint)
          .toSeq
      )
      .foreach(println)

    def toPoint(s: String) =
      s.split(",").map(_.toInt).grouped(2).map(xy => (xy(0), xy(1)))
}
