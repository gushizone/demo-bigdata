package tk.gushizone.scala.objects

object EnumTest {

    def main(args: Array[String]): Unit = {

        println(Signal.GREEN)

    }


}

/**
 * 枚举
 */
object Signal extends Enumeration {
    val GREEN, YELLOW, RED = Value
}
