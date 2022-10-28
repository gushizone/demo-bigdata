package tk.gushizone.bigdata.spark.scala

object WordCount extends App {

  val spark = SparkHelper.sparkSession

  val file = spark.sparkContext.textFile(SparkHelper.resourcesPaths.wcTxt)
  val wordCounts = file.flatMap(line => line.split(",")).map(word => (word, 1)).reduceByKey(_ + _)
  wordCounts.collect

  // 便于 SparkUI 查看
  Thread.sleep {
    Long.MaxValue
  }

}
