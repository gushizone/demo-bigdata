package tk.gushizone.scala.implicits

import scala.language.implicitConversions


object ImplicitTest {

    def main(args: Array[String]): Unit = {

        // 使用内部隐式转换
        val x:Int = 3.5

        // 引入外部隐式转换(推荐放在靠近变量的地方, 使作用域尽可能的小)
        import tk.gushizone.scala.implicits.ImplicitTest2._
        val p:Person = "foo"
        println(p)
    }

    /**
     * 自定义隐式转换函数
     *
     * - 可以增强现有类库功能
     */
    implicit def double2Int(d:Double):Int = d.toInt

}
