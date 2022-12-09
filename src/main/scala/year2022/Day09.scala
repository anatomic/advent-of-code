package year2022

import java.time.Instant
import scala.collection.mutable
import scala.io.Source

object Day09 {
  type Point = (Int, Int)
  val input = Source.fromResource("2022/09.txt").getLines().toVector
  def main(args: Array[String]): Unit =
    val a = System.nanoTime()
    println(visitedCount(2))
    val b = System.nanoTime()
    println(visitedCount(10))
    val c = System.nanoTime()
    println(visitedCountImp(2))
    val d = System.nanoTime()
    println(visitedCountImp(10))
    val e = System.nanoTime()

    def t(s: Long, e: Long) = (e - s) / 1_000_000
    println("Timings")
    println(s"rope = 2, algo = fp, ${t(a, b)}ms")
    println(s"rope = 10, algo = fp, ${t(b, c)}ms")
    println(s"rope = 2, algo = imperative, ${t(c, d)}ms")
    println(s"rope = 10, algo = imperative, ${t(d, e)}ms")

  private def visitedCount(n: Int) =
    input
      .flatMap { case s"$d $v" => d * v.toInt }
      .scanLeft(List.fill(n)((0, 0))) { case (snake, m) =>
        snake.tail.scanLeft(moveHead(snake.head, m)) { case (a, b) =>
          if (a._1 - b._1).abs <= 1 && (a._2 - b._2).abs <= 1 then b
          else (b._1 + (a._1 - b._1).sign, b._2 + (a._2 - b._2).sign)
        }
      }
      .map(_.last)
      .distinct
      .size

  private def moveHead(p: Point, cmd: Char) = cmd match
    case 'U' => (p._1, p._2 + 1)
    case 'D' => (p._1, p._2 - 1)
    case 'R' => (p._1 + 1, p._2)
    case 'L' => (p._1 - 1, p._2)

  private def visitedCountImp(n: Int) =
    val snake = mutable.ArrayBuffer.fill(n)((0, 0))
    val visited = mutable.Set[Point]((0, 0))
    for i <- input.flatMap { case s"$d $v" => d * v.toInt }
    do
      val h = snake.head
      i match
        case 'U' => snake(0) = (h._1, h._2 + 1)
        case 'D' => snake(0) = (h._1, h._2 - 1)
        case 'L' => snake(0) = (h._1 - 1, h._2)
        case 'R' => snake(0) = (h._1 + 1, h._2)

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
