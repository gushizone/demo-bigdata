package tk.gushizone.scala.clazz

import scala.beans.BeanProperty

object ClassTestV2 {

    def main(args: Array[String]): Unit = {

        val person = new Person()
        person.setName("foo")
        person.setAge(18)

        person.show()

    }

    /**
     * 1. 使用 `_` 设置初始化默认值
     * 2. 使用 @BeanProperty 自动生成 getter and setter
     */
    class Person {

        @BeanProperty var name: String = _

        @BeanProperty var age: Int = _

        def show(): Unit = println(s"我叫$name, 今年${age}岁")
    }


}
