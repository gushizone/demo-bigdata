package tk.gushizone.bigdata.spark.scala.streaming.flume

import org.apache.spark.SparkConf
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 使用自定义接收器的基于拉的方法获取数据
 *
 * 注意: 本地环境 flume 1.9.0(scala 2.11), scala 2.12 存在冲突和缺包
 * 需要将 resources/jar 拷贝到  echo $FLUME_HOME/lib 下, 并删除旧 jar
 */
object PullBasedWordCount {

  /**
   * 1. 启动 flume
   * 2. spark-submit 本类
   * 3. echo
   *
   * echo "Spark Streaming + Flume" >> /tmp/log.txt
   */
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf()
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    // 1.获取输入流
    val flumeStream = FlumeUtils.createPollingStream(ssc, "127.0.0.1", 8888)

    // 2.打印输入流中的数据
    flumeStream.map(line => new String(line.event.getBody.array()).trim).print()

    ssc.start()
    ssc.awaitTermination()
  }

}