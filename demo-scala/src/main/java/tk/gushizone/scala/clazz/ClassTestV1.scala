package tk.gushizone.scala.clazz

object ClassTestV1 {

    def main(args: Array[String]): Unit = {

        val person = new Person()
        person.setName("foo")
        person.setAge(18)

        person.show()

    }

    class Person {

        private var name: String = _

        private var age: Int = 0

        def getName: String = this.name

        def setName(name: String): Unit = this.name = name

        def getAge: Int = this.age

        def setAge(age: Int): Unit = this.age = age

        def show(): Unit = println(s"我叫$name, 今年${age}岁")
    }


}
