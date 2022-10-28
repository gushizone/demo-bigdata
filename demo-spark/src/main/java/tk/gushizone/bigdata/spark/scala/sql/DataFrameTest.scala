package tk.gushizone.bigdata.spark.scala.sql

import tk.gushizone.bigdata.spark.scala.SparkHelper

object DataFrameTest {

  def main(args: Array[String]): Unit = {

    val spark = SparkHelper.sparkSession
    val df = spark.read.json(SparkHelper.resourcesPaths.empJson)
    df.show()
  }

}
