package tk.gushizone.scala.func

object FuncTest2 {


    def main(args: Array[String]): Unit = {

        Hi("foo", msg => println(msg))
    }

    /**
     * 函数作为参数传递
     */
    def Hi(msg: String, func: String => Unit): Unit = {
        func(s"Hi~ $msg")
    }

}
