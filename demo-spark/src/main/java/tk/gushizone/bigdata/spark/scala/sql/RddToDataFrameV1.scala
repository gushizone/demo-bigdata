package tk.gushizone.bigdata.spark.scala.sql

import org.apache.spark.sql.Dataset
import tk.gushizone.bigdata.spark.scala.SparkHelper

/**
 * 由RDD创建DataFrame - 使用反射推断
 */
object RddToDataFrameV1 extends App {

  val spark = SparkHelper.sparkSession
  // 1.导入隐式转换
  import spark.implicits._


  // 2.创建部门类
  case class Dept(deptno: Long, dname: String, loc: String)

  // 3.创建 RDD 并转换为 dataSet
  val rddToDS: Dataset[Dept] = spark.sparkContext
    .textFile(SparkHelper.resourcesPaths.empTxt)
    .map(_.split("\t"))
    .map(line => Dept(line(0).trim.toLong, line(1), line(2)))
    .toDS() // 如果调用 toDF() 则转换为 dataFrame

  rddToDS.show()


  // DataFrames 与 Datasets 互相转换
  var ds = rddToDS.as[Dept]
  var df = ds.toDF()

  ds.show()
  df.show()

}
