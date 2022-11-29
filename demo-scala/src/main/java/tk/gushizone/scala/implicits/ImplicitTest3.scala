package tk.gushizone.scala.implicits

object ImplicitTest3 {

    def main(args: Array[String]): Unit = {

        val list = List(4, 2, 1, 5, 3)

        // 隐式转换参数可不传
        val list2 = list.sorted

        println(s"list: $list")
        println(s"list2: $list2")

        // 降序
        implicit val ord = new Ordering[Int](){
            override def compare(x: Int, y: Int): Int = y.compare(x)
        }
        // 隐式转换参数会在作用域内自动传递
        val list3 = list.sorted
        println(s"list3: $list3")
    }

}
