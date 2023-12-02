package year2021

object Day17 {

  val test = "target area: x=20..30, y=-10..-5"
  val input ="target area: x=241..273, y=-97..-63"

  private val parser = "target area: x=(\\d+)..(\\d+), y=(-?\\d+)..(-?\\d+)".r
  def main(args: Array[String]): Unit = {
    part1(input)
    part2(input)
  }

  private def part1(input: String) =
    val nat = (n: Int) => (n * (n + 1)) / 2
    input match
      case parser(_, _, y1, _) => println(nat(y1.toInt))

  private def part2(input: String) =
    val (x1, x2, y1, y2) = input match
      case parser(x1, x2, y1, y2) => (x1.toInt, x2.toInt, y1.toInt, y2.toInt)

    val candidates = for
      x <- 0 to x2
      y <- y1 to Math.abs(y1)
      if anyMatch(x, y, x1, x2, y1, y2)
    yield
      (x, y)

    println(candidates.size)

  private def anyMatch(u: Int, v: Int, x1: Int, x2: Int, y1: Int, y2: Int): Boolean =
    var x, y = 0
    var (xv, yv) = (u, v)
    var stillPossible = true

    while (stillPossible)
      x += xv
      y += yv
      xv = if xv > 0 then xv - 1 else 0
      yv -= 1
      if (((x1 <= x) && (x <= x2)) && ((y1 <= y) && (y <= y2)))
        return true
      else if ((xv == 0 && ((x < x1) || (x > x2))) || (y < y1))
        stillPossible = false

    false
}
