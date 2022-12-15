package year2022

import scala.collection.mutable
import scala.collection.mutable.PriorityQueue
import scala.io.Source

object Day12 {
  val input =
    Source
      .fromResource("2022/12.txt")
      .getLines()
      .map(_.toCharArray().toSeq)
      .toSeq

  private val START = 'S'
  private val TARGET = 'E'

  opaque type Point = (Int, Int)
  opaque type Grid = Seq[Seq[Char]]

  def main(args: Array[String]): Unit =
    println(solve(Seq('S')))
    println(solve(Seq('S', 'a')))

  private def solve(startLetters: Seq[Char]) =
    (for
      (row, y) <- input.zipWithIndex
      (char, x) <- row.zipWithIndex
      if startLetters.contains(char)
    yield (x, y)).flatMap(findPath(input, _)).min

  private def findPath(grid: Grid, s: Point): Option[Int] =
    val toCheck = mutable
      .PriorityQueue[(Point, Int)]((s, 0))(
        Ordering.by((_, score) => score)
      )
      .reverse
    val visited = mutable.Set[Point]()

    while toCheck.nonEmpty do
      val ((x, y), score) = toCheck.dequeue()
      if grid(y)(x) == TARGET then return Some(score)

      if !visited.contains((x, y)) then
        visited += ((x, y))
        toCheck ++= neighbours(grid, (x, y)).map((_, score + 1))

    None

  private def neighbours(grid: Grid, p: Point): Seq[Point] =
    Seq(
      (p._1, p._2 + 1),
      (p._1, p._2 - 1),
      (p._1 + 1, p._2),
      (p._1 - 1, p._2)
    ).filter(t =>
      inBounds(grid.size, grid.head.size, t) && isAllowed(
        grid(p._2)(p._1),
        grid(t._2)(t._1)
      )
    )

  private def inBounds(rows: Int, cols: Int, p: Point): Boolean =
    (p._1 >= 0 && p._1 < cols) && (p._2 >= 0 && p._2 < rows)

  private def isAllowed(a: Char, b: Char): Boolean =
    (normalise(b) - normalise(a)) < 2

  private def normalise(c: Char): Char =
    c match
      case x if x == START  => 'a'
      case x if x == TARGET => 'z'
      case _                => c

}
