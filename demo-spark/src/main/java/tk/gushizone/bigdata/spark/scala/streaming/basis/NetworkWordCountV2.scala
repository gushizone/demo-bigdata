package tk.gushizone.bigdata.spark.scala.streaming.basis

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 词频统计升级版 - 记忆式
 * updateStateByKey
 */
object NetworkWordCountV2 {


  def main(args: Array[String]) {

    /*
     * 本地测试时最好指定hadoop用户名,否则会默认使用本地电脑的用户名,
     * 此时在HDFS上创建目录时可能会抛出权限不足的异常
     */
    System.setProperty("HADOOP_USER_NAME", "root")

    /*指定时间间隔为5s*/
    val sparkConf = new SparkConf().setAppName("NetworkWordCountV2").setMaster("local[2]")
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    /*必须要设置检查点*/
    // 它会去检查点中取出上一次保存的信息，并使用自定义的 updateFunction 函数将上一次的数据和本次数据进行相加，然后返回。
    ssc.checkpoint("hdfs://127.0.0.1:8020/spark-streaming")

    /*创建文本输入流,并进行词频统计*/
    val lines = ssc.socketTextStream("127.0.0.1", 9999)
    lines.flatMap(_.split(" ")).map(x => (x, 1))
      .updateStateByKey[Int](updateFunction _)
      .print()

    /*启动服务*/
    ssc.start()
    /*等待服务结束*/
    ssc.awaitTermination()

  }

  /**
   * 累计求和
   *
   * @param currentValues 当前的数据
   * @param preValues     之前的数据
   * @return 相加后的数据
   */
  def updateFunction(currentValues: Seq[Int], preValues: Option[Int]): Option[Int] = {
    val current = currentValues.sum
    val pre = preValues.getOrElse(0)
    Some(current + pre)
  }

}