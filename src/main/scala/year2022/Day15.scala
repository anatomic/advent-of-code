package year2022

import year2022.Day15.Item.Sensor

import scala.annotation.tailrec
import scala.io.Source

object Day15 {
  opaque type Point = (Int, Int)
  private val (sensors, beacons) = Source
    .fromResource("2022/15.txt")
    .getLines()
    .map { case s"Sensor at x=$sx, y=$sy: closest beacon is at x=$bx, y=$by" =>
      val a = sx.toInt
      val b = sy.toInt
      val c = bx.toInt
      val d = by.toInt
      Seq(Item.Sensor(a, b, manhatten((a, b), (c, d))), Item.Beacon(c, d))
    }
    .foldLeft((Seq[Item.Sensor](), Seq[Item.Beacon]())) {
      case ((sensors, beacons), Seq(sensor, beacon)) =>
        (
          sensors :+ sensor.asInstanceOf[Item.Sensor],
          beacons :+ beacon.asInstanceOf[Item.Beacon]
        )
    }

  enum Item:
    case Sensor(val x: Int, val y: Int, r: Int)
    case Beacon(val x: Int, val y: Int)

    def range: Int = this match
      case Sensor(_, _, r) => r
      case _               => 1

    def inRange(p: Point): Boolean = manhatten(this.toPoint, p) <= range

    def toPoint: Point = this match
      case Sensor(x, y, _) => (x, y)
      case Beacon(x, y)    => (x, y)

  private def pointsForRow(row: Int)(s: Item): Seq[Point] = s match
    case Item.Sensor(x, y, range) if y + range >= row || y - range <= row =>
      val dy = Math.abs(row - y)
      for dx <- x - (range - dy) to x + (range - dy)
      yield (dx, row)
    case _ => Seq()

  private def part1(row: Int = 2000000) =
    println(
      (sensors.flatMap(pointsForRow(row)).toSet -- beacons
        .map(_.toPoint)
        .toSet).size
    )

  private def manhatten(a: Point, b: Point) =
    Math.abs(a._1 - b._1) + Math.abs(a._2 - b._2)

  private def freq(p: Point) = p._1.toLong * 4_000_000L + p._2.toLong

  private def shell(p: Item) =
    p match
      case Item.Sensor(x, y, r) =>
        (for
          dx <- 1 to r + 1
          dy = r + 1 - dx
        yield Seq(
          (x + dx, y - dy),
          (x + dx, y + dy),
          (x - dx, y - dy),
          (x - dx, y + dy)
        ))
      case _ => Seq()

  private def inBounds(size: Int, p: Point) =
    (p._1 >= 0 && p._1 <= size) && (p._2 >= 0 && p._2 <= size)

  @tailrec
  private def check(
      all: Seq[Item.Sensor],
      toCheck: Seq[Item.Sensor],
      size: Int
  ): Option[Long] =
    val p = for
      candidates <- shell(toCheck.head)
      candidate <- candidates
      if inBounds(size, candidate)
      if all.forall(_.inRange(candidate) == false)
    yield candidate

    if p.nonEmpty then Some(freq(p.head))
    else if toCheck.size == 1 then None
    else check(all, toCheck.tail, size)

  private def part2(size: Int) = {
    val s = sensors.sortBy(_.range).reverse
    check(s, s, size).foreach(println)
  }

  def main(args: Array[String]): Unit =
    part1()
    part2(4000000)
}
