package tk.gushizone.bigdata.flink.basis.scala

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * 流处理
 */
object WordCountStreaming {

  /**
   * 1. nc -lk 9999
   * 2. 启动本程序
   * 3. nc shell 输入 resources/wc.txt 内容
   */
  def main(args: Array[String]): Unit = {

    val senv = StreamExecutionEnvironment.getExecutionEnvironment
    val dataStream: DataStream[String] = senv.socketTextStream("127.0.0.1", 9999, '\n')
    dataStream.flatMap { line => line.toLowerCase.split(",") }
              .filter(_.nonEmpty)
              .map { word => (word, 1) }
              .assignAscendingTimestamps(_ => System.nanoTime())
              .keyBy(0)
              .timeWindow(Time.seconds(3))
              .sum(1)
              .print()
    senv.execute("Streaming WordCount")
  }
}