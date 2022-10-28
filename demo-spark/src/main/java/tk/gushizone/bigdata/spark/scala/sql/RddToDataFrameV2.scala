package tk.gushizone.bigdata.spark.scala.sql

import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SparkSession}
import tk.gushizone.bigdata.spark.scala.SparkHelper

/**
 * 由RDD创建DataFrame - 以编程方式指定Schema
 */
object RddToDataFrameV2 extends App {

  val spark: SparkSession = SparkHelper.sparkSession


  // 1.定义每个列的列类型
  val fields = Array(StructField("deptno", LongType, nullable = true),
    StructField("dname", StringType, nullable = true),
    StructField("loc", StringType, nullable = true))

  // 2.创建 schema
  val schema = StructType(fields)

  // 3.创建 RDD
  val deptRDD = spark.sparkContext.textFile(SparkHelper.resourcesPaths.empTxt)
  val rowRDD = deptRDD.map(_.split("\t")).map(line => Row(line(0).toLong, line(1), line(2)))


  // 4.将 RDD 转换为 dataFrame
  val deptDF = spark.createDataFrame(rowRDD, schema)
  deptDF.show()

}
