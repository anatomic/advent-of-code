package year2015

import scala.io.Source

object Day02 {
  val input = Source
    .fromResource("2015/02.txt")
    .getLines()
    .map { case s"${l}x${w}x$h" =>
      (l.toLong, w.toLong, h.toLong)
    }
    .toVector
  def main(args: Array[String]): Unit =
    println(part1)
    println(part2)

  def part1 =
    input
      .map((l, w, h) => List(2 * l * w, 2 * w * h, 2 * h * l))
      .map(d => d.sum + d.min / 2)
      .sum

  def part2 =
    input
      .map((l, w, h) => List(l, w, h).sorted)
      .map(d => d.take(2).map(_ * 2).sum + d.product)
      .sum

}
