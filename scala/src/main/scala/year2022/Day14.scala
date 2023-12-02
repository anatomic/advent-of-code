package year2022

import java.util.Map.Entry
import scala.annotation.tailrec
import scala.collection.mutable
import scala.io.Source

object Day14 {
  opaque type Point = (Int, Int)
  private val obstacles =
    Source
      .fromResource("2022/14.txt")
      .getLines()
      .map(
        _.split(" -> ")
          .map(toPoint)
          .toSeq
      )
      .flatMap(_.sliding(2).flatMap { case Seq((a, b), (c, d)) =>
        for
          x <- Math.min(a, c) to Math.max(a, c)
          y <- Math.min(b, d) to Math.max(b, d)
        yield (x, y)
      })
      .toSet
  private val Entry = (500, 0)

  def main(args: Array[String]): Unit = {
    val p1 = fill(obstacles, obstacles.maxBy(_._2)._2)
    val p2 = fill(obstacles + Entry, obstacles.maxBy(_._2)._2 + 1, 2)
    println((p1 -- obstacles).size)
    println((p2 -- obstacles).size)
  }

  @tailrec
  def fill(obstacles: Set[Point], bottom: Int, part: Int = 1): Set[Point] =
    addSand(obstacles, bottom, Entry, part) match
      case Some(location) => fill(obstacles + location, bottom, part)
      case None           => obstacles

  @tailrec
  private def addSand(
      obstacles: Set[Point],
      bottom: Int,
      loc: Point,
      part: Int
  ): Option[Point] =
    val (x, y) = loc
    if (y == bottom) && (part == 1) then None
    else if (y == bottom) && (part == 2) then Some(loc)
    else if !obstacles.contains((x, y + 1)) then
      addSand(obstacles, bottom, (x, y + 1), part)
    else if !obstacles.contains((x - 1, y + 1)) then
      addSand(obstacles, bottom, (x - 1, y + 1), part)
    else if !obstacles.contains((x + 1, y + 1)) then
      addSand(obstacles, bottom, (x + 1, y + 1), part)
    else if y == 0 then None
    else Some(loc)

  private def toPoint(s: String) = s match
    case s"$x,$y" => (x.toInt, y.toInt)
}
