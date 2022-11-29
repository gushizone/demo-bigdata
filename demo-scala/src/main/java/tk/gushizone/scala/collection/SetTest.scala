package tk.gushizone.scala.collection

import scala.collection.SortedSet

object SetTest {

    /**
     * 部分方法同 List
     */
    def main(args: Array[String]): Unit = {

        // 无序 set
        val set = Set[Int](1, 2, 3, 4, 5) ++ Set[Int](2, 4)
        println(s"set: $set")

        // 无序 set
        val sortedSet = SortedSet[Int](1, 2, 3, 4, 5) ++ SortedSet[Int](2, 4)
        println(s"sortedSet: $sortedSet")

    }


}
