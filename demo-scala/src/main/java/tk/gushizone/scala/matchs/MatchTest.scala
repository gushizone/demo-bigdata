package tk.gushizone.scala.matchs

object MatchTest {


    /**
     * match 类似 switch
     */
    def main(args: Array[String]): Unit = {

        println(matchV1("two"))
        println(matchV1("test"))
        println(matchV1(1))
        println(matchV1(6))

    }

    def matchV1(x: Any): Any = x match {
        case 1 => "one"
        case "two" => 2
        case y: Int => "scala.Int" // 类型匹配
        case _ => "many"
    }

}
