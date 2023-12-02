package year2022

import java.time.Instant
import scala.collection.mutable
import scala.io.Source

object Day09 {
  type Point = (Int, Int)
  val input = Source
    .fromResource("2022/09.txt")
    .getLines()
    .flatMap { case s"$d $v" =>
      d * v.toInt
    }
    .map(moves)
    .toSeq

  val moves = Map[Char, Point](
    'U' -> (0, 1),
    'D' -> (0, -1),
    'L' -> (-1, 0),
    'R' -> (1, 0)
  )

  def main(args: Array[String]): Unit =
    println(visitedCount(2))
    println(visitedCount(10))

  private def visitedCount(n: Int) =
    input
      .scanLeft(List.fill(n)((0, 0))) { case (snake, m) =>
        snake.tail.scanLeft(
          (snake.head._1 + m._1, snake.head._2 + m._2)
        ) { case (a, b) =>
          if (a._1 - b._1).abs <= 1 && (a._2 - b._2).abs <= 1 then b
          else (b._1 + (a._1 - b._1).sign, b._2 + (a._2 - b._2).sign)
        }
      }
      .map(_.last)
      .distinct
      .size

  private def visitedCountImp(n: Int) =
    val snake = mutable.ArrayBuffer.fill(n)((0, 0))
    val visited = mutable.Set[Point]((0, 0))
    for i <- input
    do
      snake(0) = (snake.head._1 + i._1, snake.head._2 + i._2)

      for k <- 1 until snake.size
      do
        val t = snake(k)
        val dx = snake(k - 1)._1 - t._1
        val dy = snake(k - 1)._2 - t._2

        if dx.abs > 1 || dy.abs > 1 then
          snake(k) = (t._1 + dx.sign, t._2 + dy.sign)
      visited += snake.last
    visited.size
}
