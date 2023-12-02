package year2022

import scala.annotation.tailrec
import scala.io.Source

object Day13 {
  private val input = Source
    .fromResource("2022/13.txt")
    .getLines()
    .filter(_.nonEmpty)
    .map(_.replace("10", "a"))
    .toSeq

  def main(args: Array[String]): Unit = {
    part1()
    part2()
  }

  private def part1(): Unit =
    println(
      input
        .grouped(2)
        .zipWithIndex
        .collect { case (Seq(a, b), i) =>
          if compare(a, b) then i + 1 else 0
        }
        .sum
    )

  private def part2(): Unit =
    println(
      (input ++ Seq("[[2]]", "[[6]]"))
        .sortWith(compare)
        .zipWithIndex
        .collect {
          case (s"[[$x]]", i) if (x == "2") || (x == "6") => i + 1
        }
        .product
    )

  @tailrec
  private def compare(l: String, r: String): Boolean =
    (l.head, r.head) match
      case (a, b) if a == b => compare(l.tail, r.tail)
      case (']', _)         => true
      case (_, ']')         => false
      case ('[', b)         => compare(l.tail, b + "]" + r.tail)
      case (a, '[')         => compare(a + "]" + l.tail, r.tail)
      case (a, b)           => a < b
}
