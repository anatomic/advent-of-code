package year2022

import scala.annotation.tailrec
import scala.io.Source
import scala.math.Ordered.orderingToOrdered
import scala.util.parsing.combinator.RegexParsers

object Day13_2 {
  private val input =
    Source
      .fromResource("2022/13.txt")
      .getLines()
      .filter(_.nonEmpty)
      .map(PacketParser.parseAll(PacketParser.packet, _).get)
      .toSeq
  enum Packet:
    case Item(value: Int)
    case List(values: Seq[Packet])

  private object PacketParser extends RegexParsers {
    def packet: Parser[Packet] =
      "\\d+".r ^^ { value => Packet.Item(value.toInt) } | "[" ~> repsep(
        packet,
        ","
      ) <~ "]" ^^ { Packet.List(_) }
  }

  given Ordering[Packet] with
    def compare(x: Packet, y: Packet): Int = (x, y) match
      case (Packet.Item(a), Packet.Item(b)) => a.compare(b)
      case (Packet.List(a), Packet.List(b)) => a.compare(b)
      case (Packet.Item(_), Packet.List(_)) => compare(Packet.List(Seq(x)), y)
      case (Packet.List(_), Packet.Item(_)) => compare(x, Packet.List(Seq(y)))

  def main(args: Array[String]): Unit =
    println(part1)
    println(part2)

  private def part1 =
    input
      .grouped(2)
      .zipWithIndex
      .collect { case (Seq(x, y), i) =>
        if x <= y then i + 1 else 0
      }
      .sum

  private val marker1 = Packet.List(Seq(Packet.List(Seq(Packet.Item(2)))))
  private val marker2 = Packet.List(Seq(Packet.List(Seq(Packet.Item(6)))))

  private def part2 =
    (input ++ Seq(marker1, marker2)).sorted.zipWithIndex.collect {
      case (a, i) if (a == marker1) || (a == marker2) => i + 1
    }.product
}
