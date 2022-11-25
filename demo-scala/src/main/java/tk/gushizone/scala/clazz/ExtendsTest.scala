package tk.gushizone.scala.clazz

import scala.beans.BeanProperty

object ExtendsTest {

    def main(args: Array[String]): Unit = {

        new Dog("foo").show()

    }

}

class Animal {

    @BeanProperty var name: String = _

    @BeanProperty var age: Int = _

    def this(name: String, age: Int) {
        this()
        this.name = name
        this.age = age
    }

    def show(): Unit = println(s"我叫$name, 今年${age}岁")
}

class Dog extends Animal {

    def this(name: String) {
        this()
        this.name = name
    }

    override def show(): Unit = {
        // super 只能调用父类方法, 不能调用父类构造/属性
        super.show()
        println("重写方法需要使用 override")
    }
}