package tk.gushizone.scala.func

object FuncTest3 {

    def main(args: Array[String]): Unit = {

        val arr = 1 to 5

        // foreach
        println("-"*10 + " foreach " + "-"*10)
        arr.foreach(println)
        println(arr.mkString("[", ",", "]"))

        // map: 一对一
        println("-"*10 + " map " + "-"*10)
        val res = arr.map(e => e * 10)
        println(res.mkString("[", ",", "]"))

        // flatMap: 一对多
        println("-"*10 + " flatMap " + "-"*10)
        val arr2 = Array("hi 1", "hi 2", "hi 3")
        val res2 =  arr2.flatMap(e => e.split(" "))
        println(arr2.mkString("Array(", ", ", ")"))
        println(res2.mkString("Array(", ", ", ")"))

        // reduce: 聚合 (reduceLeft)
        println("-"*10 + " reduce " + "-"*10)
        val num = arr.reduce((a, b) => {
            val res = a + b
            println(s"$a + $b = " + res)
            res
        })
        // val num = arr.reduce(_ + _)
        println(num)

        // reduce: 自右向左聚合
        println("-"*10 + " reduceRight " + "-"*10)
        val num2 = arr.reduceRight((a, b) => {
            val res = a + b
            println(s"$a + $b = " + res)
            res
        })
        println(num2)

        // dropWhile: 删除直到条件满足
        println("-"*10 + " dropWhile " + "-"*10)
        val res3 = arr.dropWhile(e => e < 3)
        println(res3)

        // sortWith: 排序
        println("-"*10 + " sortWith " + "-"*10)
        val arr3 = Array(3, 1, 2, 5, 4)
        val res4 = arr3.sortWith(_ < _)
        println(res4.mkString("Array(", ", ", ")"))

        // groupBy: 分组
        println("-"*10 + " groupBy " + "-"*10)
        val arr4 = Array("hi 1", "hi 2", "hi 1", "hi 3")
        val map = arr4.groupBy(e => e)
        map.foreach(e => println(e._1, e._2.mkString("Array(", ", ", ")")))

        // partition: 分区
        println("-"*10 + " partition " + "-"*10)
        val arr5 = 1 to 10
        val (left, right) = arr5.partition(e => e % 2 != 0)
        println(left.mkString("Array(", ", ", ")"))
        println(right.mkString("Array(", ", ", ")"))

        // 单词统计
        println("="*10 + " wordCount " + "="*10)
        val words = Array("h1", "h2", "h1", "h3", "h1", "h3")
        val wordCount = words.groupBy(e => e).map(e => (e._1, e._2.length))
        println(wordCount)
    }

}
