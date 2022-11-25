package tk.gushizone.scala.func

object FuncTest1 {


    def main(args: Array[String]): Unit = {

        func()
    }

    def func(): Unit = {

        // 函数中可以嵌套函数, 相当于局部变量
        def hi(msg: String): String = {
            println(s"Hi~ $msg")
            "end"
        }

        val sayHi = hi _ // 函数赋给变量
        sayHi("sayHi")

        def sayHi2 = hi _ // 函数赋给函数

        sayHi2("sayHi2")

        val sayHi3 = sayHi2 // 函数的二次赋值, 不需要 `_`
        sayHi3("sayHi3")

        val sayHi4 = sayHi3 // 变量赋值给变量, 不需要 `_`
        sayHi4("sayHi4")
    }

}
