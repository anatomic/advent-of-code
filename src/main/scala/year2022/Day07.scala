package year2022

import scala.collection.mutable
import scala.io.Source
import scala.util.Using
import scala.util.matching.Regex

object Day07 {
  def main(args: Array[String]): Unit = {
    val dirs = parseInput("2022/day07.txt")
    val part1 = dirs.values.filter(_ < 100000).sum
    println(part1)

    val part2 = dirs.values.toList.sorted
      .find(_ > 30000000 - (70000000 - dirs.values.max))
    part2.foreach(println)
  }

  val file: Regex = "(\\d+) (.+)".r

  private def parseInput(fileName: String) =
    Using(Source.fromResource(fileName)) { input =>
      input
        .getLines()
        .drop(1)
        .foldLeft(
          ("", mutable.Map[String, Long]())
        ) {
          case ((cwd, files), s"$$ cd $dir") => (nextPath(cwd, dir), files)
          case ((cwd, files), file(size, _)) =>
            val s = size.toLong
            cwd
              .split("/")
              .foldLeft("") { case (k, part) =>
                val p = k + "/" + part
                val total = files.getOrElse(p, 0L)
                files(p) = s + total
                p
              }
            (cwd, files)
          case (agg, _) => agg
        }
    }.get._2

  private def nextPath(cwd: String, p: String) = p match
    case ".." => cwd.split("/").init.mkString("/")
    case "/"  => "/"
    case _    => cwd + "/" + p
}
