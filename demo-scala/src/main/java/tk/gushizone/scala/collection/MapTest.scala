package tk.gushizone.scala.collection

import scala.collection.mutable
import scala.io.Source

object MapTest {


    def main(args: Array[String]): Unit = {

        // 方式一
        val lines = Source.fromFile(MapTest.getClass.getClassLoader.getResource("wc.txt").getPath).getLines()
        val map = mutable.Map[String, Int]()
        for (line <- lines ) {

            val words = line.split(",")
            for (word <- words) {
                val count = map.getOrElse(word, 0)
                map.put(word, count + 1)
            }
        }
        map.foreach(e => println(s"${e._1} : ${e._2}"))

        println("-"*10)

        // 方式二
        Source.fromFile(MapTest.getClass.getClassLoader.getResource("wc.txt").getPath).getLines()
            .flatMap(_.split(",")).toArray
            .groupBy(e => e)
            .map(e => (e._1, e._2.length))
            .foreach(e => println(s"${e._1} : ${e._2}"))
    }
}
