package tk.gushizone.scala.objects

object CompanionTestV1 {

    def main(args: Array[String]): Unit = {

        // 使用伴生对象创建类的对象, 并调用方法; 这里伴生对象相当于工厂
        Person("foo").show()
    }

}

class Person(name: String, age: Int) {

    var desc: String = "..."

    def show(): Unit = {
        println(s"我叫$name, 今年 $age 岁, $desc")
    }
}

object Person {

    /**
     * 如果希望使用伴生对象创建类的对象, 必须重写 apply 方法
     */
    def apply(name: String): Person = {
        new Person(name, 18)
    }
}