package year2022

import scala.io.Source
import scala.util.Using

object Day05_2 {
  private val instructionTest = "move (\\d+) from (\\d+) to (\\d+)".r

  def main(args: Array[String]): Unit = {
    val now = System.nanoTime()
    Using(Source.fromResource("2022/05.txt")) { input =>
      val lines = input.getLines()
      val table = lines.takeWhile(_.nonEmpty).toList.reverse.drop(1)
      val startingStacks = table
        .map(_.padTo(table.head.length, ' ').grouped(4).map(_(1)))
        .foldLeft(List.fill(table.head.grouped(4).size)("")) { (stacks, line) =>
          stacks
            .zip(line)
            .map((stack, char) => if char.isLetter then stack + char else stack)
        }

      val (part1, part2) = lines.foldLeft((startingStacks, startingStacks)) {
        case ((part1, part2), instructionTest(c, f, t)) =>
          val (count, from, to) = (c.toInt, f.toInt - 1, t.toInt - 1)
          (
            part1
              .updated(to, part1(to) + part1(from).takeRight(count).reverse)
              .updated(from, part1(from).dropRight(count)),
            part2
              .updated(to, part2(to) + part2(from).takeRight(count))
              .updated(from, part2(from).dropRight(count))
          )
      }
      println(part1.map(_.lastOption.getOrElse("")).mkString)
      println(part2.map(_.lastOption.getOrElse("")).mkString)
    }

    println(s"\nCompleted in ${(System.nanoTime() - now) / 1000000}ms")
  }
}
