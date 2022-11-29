package tk.gushizone.scala.traits

object TraitTest2 {

    def main(args: Array[String]): Unit = {

        val log = new SocketLog
        log.info("123456")
    }

}

trait Log {

    def info(msg: String): Unit

    def id(): String = {
        "Log"
    }
}

trait SecurityLog {

    def info(msg: String): Unit = {
        println("securityLog : " + msg)
    }
}

class ConsoleLog extends Log {

    override def info(msg: String): Unit = {
        println(id + " console : " + msg)
    }
}

/**
 * 特征允许多继承 (可以多次使用 with)
 */
class SocketLog extends Log with SecurityLog{

}
