package tk.gushizone.scala.string

object StringTest {

    /**
     * - s: 字符串插值
     * - f: 插值并格式化输出
     * - raw: 对字符不做任何变化的输出(不解析转义)
     */
    def main(args: Array[String]): Unit = {

        val s1 = "Tom"
        val n1 = 1.73d

        println("------ s ------")
        println(s"Hello $s1\n")
        println(s"1 + 1 = ${1 + 1}")

        println("------ f ------")
        println(f"Hello $s1%s, $n1%4.3f") // Hello Tom, 1.730

        println("------ raw ------")
        println(raw"Hello $s1\n") // Hello Tom\n


        println("------ stripMargin ------")
        val sql =
            """
              |select name, age
              |from user
              |where age > 18
              |""".stripMargin
        println(sql)


    }


}
