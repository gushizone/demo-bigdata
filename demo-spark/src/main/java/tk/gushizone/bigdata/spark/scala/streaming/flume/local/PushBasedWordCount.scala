package tk.gushizone.bigdata.spark.scala.streaming.flume.local

import org.apache.spark.SparkConf
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}


/**
 * 基于推的方法获取数据
 */
object PushBasedWordCount {

  /**
   * 1. 启动本程序
   * 2.启动 flume
   * 3. echo
   *
   * echo "Spark Streaming + Flume" >> /tmp/log.txt
   */
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf()
      .setAppName("KafkaDirectStream").setMaster("local[4]")
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    // 1.获取输入流
    val flumeStream = FlumeUtils.createStream(ssc, "127.0.0.1", 8888)

    // 2.打印输入流的数据
    flumeStream.map(line => new String(line.event.getBody.array()).trim).print()

    ssc.start()
    ssc.awaitTermination()
  }
}