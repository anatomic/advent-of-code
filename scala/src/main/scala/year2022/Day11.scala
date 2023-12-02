package year2022

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Day11 {

  val input = Source.fromResource("2022/11.txt").getLines().toSeq
  def main(args: Array[String]): Unit =
    solve(3, 20)
    solve(1, 10_000)

  private def solve(divisor: Long, iterations: Int) =
    val items: ArrayBuffer[mutable.Queue[Long]] = ArrayBuffer()
    val operations: ArrayBuffer[Long => Long] = ArrayBuffer()
    val tests: ArrayBuffer[Long] = ArrayBuffer()
    val targets: ArrayBuffer[Int] = ArrayBuffer()

    for l <- input do
      l.trim match
        case s"Starting items: $i" =>
          items += i.split(", ").map(_.toLong).to(mutable.Queue)
        case "Operation: new = old * old" => operations += ((a: Long) => a * a)
        case s"Operation: new = old + $v" =>
          operations += ((a: Long) => a + v.toLong)
        case s"Operation: new = old * $v" =>
          operations += ((a: Long) => a * v.toLong)
        case s"Test: divisible by $v"     => tests += v.toLong
        case s"If $_: throw to monkey $m" => targets += m.toInt
        case _                            =>

    val mod = tests.product
    val counts = ArrayBuffer.fill(items.length)(0L)
    for
      _ <- 1 to iterations
      (monkey, id) <- items.zipWithIndex
    do
      while monkey.nonEmpty do
        counts(id) += 1
        val newItem = (operations(id)(monkey.dequeue()) / divisor) % mod
        val target =
          if newItem % tests(id) == 0 then items(targets(id * 2))
          else items(targets((id * 2) + 1))
        target += newItem

    println(counts.sorted.reverse.take(2).product)
}
