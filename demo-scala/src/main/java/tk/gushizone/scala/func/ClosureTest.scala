package tk.gushizone.scala.func

object ClosureTest {

    /**
     * 闭包: 可以访问一个函数里面局部变量的另外一个函数。
     */
    def main(args: Array[String]): Unit = {

        // 闭包
        val j = 2
        val func = (i:Int) => i * j
        println(func(3))

        // 柯里化, 主要是为了符合函数式编程规范(函数只有一个参数)
        def fun(m:Int) = (n:Int) => n * m // 闭包(创建时)
        val foo = fun(2)    // foo = (n:Int) => n * 2
        println(foo(3))     // foo(3) = 3 => 3 * 2
        println(fun(2)(3))  // 柯里化(调用时)
    }

}
