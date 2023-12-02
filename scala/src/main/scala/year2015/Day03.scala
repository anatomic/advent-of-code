package year2015

import scala.io.Source

object Day03 {
  private val input = Source.fromResource("2015/03.txt").mkString

  def main(args: Array[String]): Unit =
    println(part1)
    println(part2)

  opaque type Point = (Int, Int)

  def part1 =
    input
      .scanLeft((0,0))(next)
      .distinct
      .size

  def part2 =
    input
      .grouped(2)
      .scanLeft(Seq((0, 0), (0, 0))) { case (Seq(santa, robot), moves) =>
        Seq(next(santa, moves.head), next(robot, moves.last))
      }
      .flatten
      .distinct
      .size

  def next(p: Point, m: Char) = m match
    case '^' => p.copy(_2 = p._2 + 1)
    case 'v' => p.copy(_2 = p._2 - 1)
    case '>' => p.copy(_1 = p._1 + 1)
    case '<' => p.copy(_1 = p._1 - 1)

}
