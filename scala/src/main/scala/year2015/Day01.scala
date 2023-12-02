package year2015

import scala.io.Source

object Day01 {
  val input = Source.fromResource("2015/01.txt").getLines().toList.head
  def main(args: Array[String]): Unit =
    println(part1)
    println(part2)

  def part1 = input.foldLeft(0) {
    case (floor, '(') => floor + 1
    case (floor, ')') => floor - 1
  }

  def part2 = input
    .scanLeft(0) {
      case (t, '(') => t + 1
      case (t, ')') => t - 1
    }
    .indexOf(-1)
}
