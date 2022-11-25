package tk.gushizone.scala.objects

object ObjectTest {

    def main(args: Array[String]): Unit = {
        println("object 内定义的方法都是静态方法, 所以 main 可以直接运行")

        // 因为静态可以直接调用
        show()
    }

    def show(): Unit ={
        println("object 内定义的方法都是静态方法")
    }
}
