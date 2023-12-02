package year2022

import scala.collection.mutable
import scala.io.Source
import scala.util.Using
import scala.util.matching.Regex

object Day07 {
  def main(args: Array[String]): Unit = {
    val dirs = parseInput("2022/07.txt")

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
        .foldLeft(
          ("", mutable.Map[String, Long]())
        ) {
          case ((cwd, files), s"$$ cd $dir") => (nextPath(cwd, dir), files)
          case ((cwd, files), file(size, _)) =>
            cwd
              .split("/")
              .foldLeft(List("")) { (k, part) =>
                s"${k.head}/$part" :: k
              }
              .foreach(p => files(p) = size.toLong + files.getOrElse(p, 0L))
            (cwd, files)
          case (agg, _) => agg
        }
    }.get._2

  private def nextPath(cwd: String, p: String) = p match
    case ".." => cwd.split("/").init.mkString("/")
    case "/" => "/"
    case _ => cwd + "/" + p
}
