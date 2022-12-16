package year2022

import scala.annotation.tailrec
import scala.io.Source

object Day16 {
  private val valves = Source
    .fromResource("2022/16.test.txt")
    .getLines()
    .map {
      case s"Valve $id has flow rate=$flow; tunnels lead to valves $valves" =>
        id -> Valve(id, flow.toInt, valves.split(", "))
      case s"Valve $id has flow rate=$flow; tunnel leads to valve $valve" =>
        id -> Valve(id, flow.toInt, Seq(valve))
    }
    .toMap

  private case class Valve(id: String, flow: Int, neighbours: Seq[String])

  @tailrec
  def plan(current: String, open: Set[String], total: Long, time: Int): Long =
    val c = valves(current)
    val next = c.neighbours
      .map(valves(_))
      .filterNot(v => open.contains(v.id))
      .sortBy(_.flow)
      .reverse

    if time == 0 then total
    else if c.flow > 0 && next.nonEmpty then
      plan(next.head.id, open + c.id, total + (time * c.flow), time - 2)
    else if c.flow > 0 && next.isEmpty then total + (time * c.flow)
    else if next.nonEmpty then plan(next.head.id, open, total, time - 1)
    else total

  def main(args: Array[String]): Unit =
    println(plan("AA", Set[String](), 0, 30))
}
