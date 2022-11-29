package tk.gushizone.scala.implicits

object ImplicitTest2 {

    case class Person(name:String)

    implicit def str2Person(name:String):Person = Person(name)

}
