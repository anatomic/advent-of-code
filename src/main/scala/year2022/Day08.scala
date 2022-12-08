package year2022

import scala.io.{BufferedSource, Source}
import scala.util.Using

object Day08 {
  def main(args: Array[String]): Unit =
    Using(Source.fromResource("2022/day08.txt")) { input =>
      val (rows, cols, grid) = parseGrid(input)
      part1(rows, cols, grid)
      part2(rows, cols, grid)
    }
}

private def part1(rows: Int, cols: Int, grid: List[Int]) =
  val result = grid.zipWithIndex
    .map((tree, idx) =>
      if isEdge(rows, cols, idx) then 1
      else
        checks(idx, rows, cols)
          .map(points =>
            if points.forall((x1, y1) => grid(y1 * cols + x1) < tree) then 1
            else 0
          )
          .max
    )
    .sum

  println(result)

private def part2(rows: Int, cols: Int, grid: List[Int]) =
  val result = grid.zipWithIndex
    .map((tree, idx) =>
      checks(idx, rows, cols)
        .map(points =>
          if points.isEmpty then 0
          else
            val s = points
              .takeWhile((x1, y1) => grid(y1 * cols + x1) < tree)
              .size
            Math.min(s + 1, points.size)
        )
        .product
    )
    .max
  println(result)

private def checks(idx: Int, rows: Int, cols: Int): Seq[Seq[(Int, Int)]] =
  val (x, y) = coords(idx, cols)
  val north =
    for y1 <- 0 until y
    yield (x, y1)

  val south =
    for y1 <- y + 1 until rows
    yield (x, y1)

  val west =
    for x1 <- 0 until x
    yield (x1, y)

  val east =
    for x1 <- x + 1 until cols
    yield (x1, y)

  List(north.reverse, east, south, west.reverse)
private def printGrid(cols: Int, grid: List[Int]) =
  grid.grouped(cols).foreach(row => println(row.mkString(" ")))

private def parseGrid(input: BufferedSource) =
  val grid = input.getLines().toList
  val cols = grid.head.size
  val rows = grid.size
  (rows, cols, grid.flatten.map(_.asDigit))

private def isEdge(rows: Int, cols: Int, idx: Int): Boolean =
  val (x, y) = coords(idx, cols)
  ((x == 0) || (x == cols - 1)) || ((y == 0) || (y == rows - 1))

private def coords(idx: Int, cols: Int): (Int, Int) =
  (idx % cols, idx / cols)
