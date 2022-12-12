package year2022

import scala.io.{BufferedSource, Source}
import scala.util.Using

object Day08 {
  def main(args: Array[String]): Unit =
    Using(Source.fromResource("2022/08.txt")) { input =>
      val (rows, cols, grid) = parseGrid(input)
      println(part1(rows, cols, grid))
      println(part2(rows, cols, grid))
    }
}

private def part1(rows: Int, cols: Int, grid: Seq[Int]) =
  grid.zipWithIndex
    .count((tree, idx) =>
      checks(coords(idx, cols), rows, cols)
        .exists(points =>
          points.forall((x1, y1) => grid(y1 * cols + x1) < tree)
        )
    )

private def part2(rows: Int, cols: Int, grid: Seq[Int]) =
  grid.zipWithIndex
    .map((tree, idx) =>
      checks(coords(idx, cols), rows, cols)
        .map(points =>
          Math.min(
            points
              .segmentLength((x1, y1) => grid(y1 * cols + x1) < tree) + 1,
            points.size
          )
        )
        .product
    )
    .max

// This is pretty bogus. Would be nicer to slice the original grid in some way
private def checks(
    coords: (Int, Int),
    rows: Int,
    cols: Int
): Seq[Seq[(Int, Int)]] =
  Vector(
    (for y1 <- 0 until coords._2
    yield (coords._1, y1)).reverse, // north
    for x1 <- coords._1 + 1 until cols
    yield (x1, coords._2), // east
    for y1 <- coords._2 + 1 until rows
    yield (coords._1, y1), // south
    (for x1 <- 0 until coords._1
    yield (x1, coords._2)).reverse // west
  )

private def parseGrid(input: BufferedSource) =
  val grid = input.getLines().toVector
  val cols = grid.head.size
  val rows = grid.size
  (rows, cols, grid.flatten.map(_.asDigit))

private def coords(idx: Int, cols: Int): (Int, Int) =
  (idx % cols, idx / cols)
