package tk.gushizone.bigdata.spark.scala.streaming.basis

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 词频统计
 *
 * Spark Streaming 编程的入口类是 StreamingContext，在创建时候需要指明 sparkConf 和 batchDuration(批次时间)，
 * Spark 流处理本质是将流数据拆分为一个个批次，然后进行微批处理，batchDuration 就是批次拆分的时间间隔。
 * 这个时间可以根据业务需求和服务器性能进行指定，如果业务要求低延迟并且服务器性能也允许，则这个时间可以指定得很短。
 *
 * 这里需要注意的是：示例代码使用的是本地模式，配置为 local[2]，这里不能配置为 local[1]。
 * 这是因为对于流数据的处理，Spark 必须有一个独立的 Executor 来接收数据，
 * 然后再由其他的 Executors 来处理，所以为了保证数据能够被处理，至少要有 2 个 Executors。
 * 这里我们的程序只有一个数据流，在并行读取多个数据流的时候，也需要保证有足够的 Executors 来接收和处理数据。
 */
object NetworkWordCount {


  /**
   * 1. 开端口; 2. 启动程序; 3. 输入单词
   *
   * nc -lk 9999
   *
   * hello world hello spark hive hive hadoop
   * storm storm flink azkaban
   */
  def main(args: Array[String]) {

    /*指定时间间隔为5s*/
    val sparkConf = new SparkConf().setAppName("NetworkWordCount").setMaster("local[2]")
    // 5s 一个批次进行统计
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    /*创建文本输入流,并进行词频统计*/
    val lines = ssc.socketTextStream("127.0.0.1", 9999)
    lines.flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _).print()

    /*启动服务*/
    ssc.start()
    /*等待服务结束*/
    ssc.awaitTermination()

  }
}