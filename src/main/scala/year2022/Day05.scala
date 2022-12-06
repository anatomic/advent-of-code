package year2022

import scala.collection.mutable
import scala.io.Source
import scala.util.Try

/**
 * [D]
 * [N] [C]
 * [Z] [M] [P]
 * 1   2   3
 *
 * move 1 from 2 to 1
 * etc
 */

object Day05 {
  def main(args: Array[String]): Unit =
    val now = System.nanoTime()
    val input = Source.fromResource("2022/day05.txt")
    val Array(stacks, moves) = input.mkString.split("\n\n")
    val result = moves.linesIterator.foldLeft(parseStacks(stacks))(part1)
    for
      i <- 1 to result.size
    do
      print(result(i).head)

    println(s"\nCompleted in ${(System.nanoTime() - now) / 1000000}ms")

  private def parseStacks(rawStacks: String) =
    rawStacks.linesIterator.toList.reverse
      .flatMap(_.zipWithIndex.filter(_._1.isLetterOrDigit))
      .groupMap(_._2 / 4 + 1)(_._1)
      .map((key, items) => (key, mutable.Stack.from(items.tail.reverse)))

  private def part1(stacks: Map[Int, mutable.Stack[Char]], instructions: String): Map[Int, mutable.Stack[Char]] =
    val (count, a, b) = parseMoves(instructions)
    stacks(b).pushAll(stacks(a).take(count))
    stacks(a).remove(0, count)
    stacks

  private def part2(stacks: Map[Int, mutable.Stack[Char]], instructions: String): Map[Int, mutable.Stack[Char]] =
    val (count, a, b) = parseMoves(instructions)
    stacks(b).pushAll(stacks(a).take(count).reverse)
    stacks(a).remove(0, count)
    stacks

  private def parseMoves(instructions: String) = instructions match
    case s"move $count from $a to $b" => (count.toInt, a.toInt, b.toInt)
}
