package tk.gushizone.scala.matchs

object CaseClassTest {

    def main(args: Array[String]): Unit = {

        val person = Person("foo", 18)

        println(person)
        println(person.name)

        // 样例类模拟枚举
        func(GREEN("绿灯"))
    }

    /**
     * 样例类(相当于 javaBean)
     *
     * 1. 自动添加特征,如 scala.Product, scala.Serializable 等
     * 2. 自动提供伴生对象功能
     * 3. 构造参数默认是 val, 可以使用 var; 且为 public, 可以直接访问
     */
    case class Person(name:String, age:Int)

    /**
     * 模拟枚举
     */
    sealed abstract class Signal(color:String)
    case class GREEN(green:String) extends Signal(green)
    case class YELLOW(yellow:String) extends Signal(yellow)
    case class RED(red:String) extends Signal(red)


    def func(signal:Signal): Unit = {
        signal match {
            case GREEN(green) => println(green)
            case YELLOW(yellow) => println(yellow)
            case RED(red) => println(red)
            case _ => println("no-match")
        }
    }
}
