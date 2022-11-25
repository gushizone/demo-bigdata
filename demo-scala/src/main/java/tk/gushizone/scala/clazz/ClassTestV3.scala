package tk.gushizone.scala.clazz

import scala.beans.BeanProperty

object ClassTestV3 {

    def main(args: Array[String]): Unit = {

        val person1 = new Person("foo", 18)
        person1.show()

        println("-"*10)

        val person2 = new Person()
        person2.show()

    }

    /**
     * 1. 使用 `_` 设置初始化默认值
     * 2. 使用 @BeanProperty 自动生成 getter and setter
     */
//    class Person() { // 其实类本身就是主构造, 且可以修改构造参数
    class Person {

        @BeanProperty var name: String = _

        @BeanProperty var age: Int = _

        println("这个是主构造器")

        def person(): Unit = {
            println("这个不是构造器!")
        }

        def this(name: String, age: Int) {
            this() // 辅助构造器一定要调用其他的构造器, 可以不是主构造器
            this.name = name
            this.age = age
            println("这个是辅助构造器")
        }

        def this(name: String) {
            this(name, 18) // 辅助构造器一定要调用其他的构造器, 可以不是主构造器
            println("这个是辅助构造器")
        }

        def show(): Unit = println(s"我叫$name, 今年${age}岁")
    }


}
