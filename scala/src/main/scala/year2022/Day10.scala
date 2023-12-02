package year2022

import scala.io.Source

object Day10 {
  val input = Source
    .fromResource("2022/10.txt")
    .getLines()
    .toVector
    .foldLeft(Vector(1)) {
      case (sum, "noop")     => sum :+ sum.last
      case (sum, s"addx $v") => sum ++: Vector(sum.last, sum.last + v.toInt)
      case (sum, _)          => sum
    }

  def main(args: Array[String]): Unit =
    println(part1)
    println(part2)

  def part2 =
    input
      .grouped(40)
      .map(
        _.zipWithIndex
          .map((col, x) => if (x - col).abs <= 1 then '#' else '.')
          .mkString
      )
      .mkString("\n")

  def part1 =
    input.zipWithIndex.collect {
      case (a, b) if (b - 19) % 40 == 0 => a * (b + 1)
    }.sum
}
