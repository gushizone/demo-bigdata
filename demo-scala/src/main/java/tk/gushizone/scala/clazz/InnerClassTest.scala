package tk.gushizone.scala.clazz

object InnerClassTest {

    def main(args: Array[String]): Unit = {

        val outer = new Outer
        val inner = new outer.Inner
        inner.show()

    }
}

class Outer {
    o =>

    val x = 5

    class Inner {
        i =>

        val x = 6

        def show(): Unit = {
            // 类 java 方式
            val x = 7
            println("x = " + x)
            println("innerClass x = " + this.x)
            println("outerClass x = " + Outer.this.x)

            println("-" * 10)

            // 别名方式
            println("innerClass x = " + i.x)
            println("outerClass x = " + o.x)
        }
    }
}
