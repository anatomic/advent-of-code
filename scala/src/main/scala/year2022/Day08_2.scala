package year2022

import scala.io.Source

object Day08_2 {
  // Initially had some bizarre desire to not use a multi-dimensional grid.
  val grid =
    Source
      .fromResource("2022/08.txt")
      .getLines()
      .map(_.map(_.asDigit).toVector)
      .toVector

  def main(args: Array[String]): Unit = {
    println(part1())
    println(part2())
  }

  def part1() =
    grid.zipWithIndex
      .map((r, y) =>
        r.zipWithIndex.count((tree, x) =>
          neighbours(x, y).exists(_.forall(_ < tree))
        )
      )
      .sum

  def part2() =
    grid.zipWithIndex
      .flatMap((r, y) =>
        r.zipWithIndex
          .map((tree, x) =>
            neighbours(x, y)
              .map(trees =>
                Math.min(trees.segmentLength(_ < tree) + 1, trees.size)
              )
              .product
          )
      )
      .max

  private def neighbours(x: Int, y: Int) =
    Vector(
      grid.take(y).map(_(x)).reverse,
      grid.drop(y + 1).map(_(x)),
      grid(y).take(x).reverse,
      grid(y).drop(x + 1)
    )
}
