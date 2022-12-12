package year2022

import scala.io.Source
import scala.collection.mutable
import scala.collection.mutable.PriorityQueue

object Day12 {
  val input =
    Source
      .fromResource("2022/12.txt")
      .getLines()
      .map(_.toCharArray().toSeq)
      .toSeq

  val START = 'S'
  val TARGET = 'E'

  type Point = (Int, Int)
  type Grid = Seq[Seq[Char]]

  def main(args: Array[String]): Unit =
    println(solve(Seq('S')))
    println(solve(Seq('S', 'a')))

  def solve(startLetters: Seq[Char]) =
    (for
      (row, y) <- input.zipWithIndex
      (char, x) <- row.zipWithIndex
      if startLetters.contains(char)
    yield (x, y)).flatMap(findPath(input, _)).min

  def findPath(grid: Seq[Seq[Char]], s: Point): Option[Int] =
    val toCheck = PriorityQueue[(Point, Int)]((s, 0))(
      Ordering.by((p, score) => score)
    ).reverse
    val visited = mutable.Set[Point]()

    while toCheck.nonEmpty do
      val ((x, y), score) = toCheck.dequeue()
      if input(y)(x) == TARGET then return Some(score)

      if !visited.contains((x, y)) then
        visited += ((x, y))
        toCheck ++= neighbours(input, (x, y)).map((_, score + 1))

    None

  def neighbours(grid: Grid, p: Point): Seq[Point] =
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

  def inBounds(rows: Int, cols: Int, p: Point): Boolean =
    (p._1 >= 0 && p._1 < cols) && (p._2 >= 0 && p._2 < rows)

  def isAllowed(a: Char, b: Char): Boolean =
    (normalise(b) - normalise(a)) < 2

  def normalise(c: Char): Char =
    c match
      case x if x == START  => 'a'
      case x if x == TARGET => 'z'
      case _                => c

}
