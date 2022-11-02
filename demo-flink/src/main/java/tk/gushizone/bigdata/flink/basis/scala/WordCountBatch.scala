package tk.gushizone.bigdata.flink.basis.scala

import org.apache.flink.api.scala._
import tk.gushizone.bigdata.flink.FlinkHelper

/**
 * 批处理
 */
object WordCountBatch {

  def main(args: Array[String]): Unit = {
    val benv = ExecutionEnvironment.getExecutionEnvironment
    val dataSet = benv.readTextFile(FlinkHelper.resourcesPaths.wcTxt)
    dataSet.flatMap { _.toLowerCase.split(",")}
            .filter (_.nonEmpty)
            .map { (_, 1) }
            .groupBy(0)
            .sum(1)
            .print()
  }
}