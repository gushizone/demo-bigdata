package tk.gushizone.bigdata.spark.scala.sql

import tk.gushizone.bigdata.spark.scala.SparkHelper
import org.apache.spark.sql.functions._

object ColumnsTest extends App {

  val spark = SparkHelper.sparkSession
  // 1.需要导入隐式转换(不能在 main 中使用)
  import spark.implicits._


  // 2.创建 case class,等价于 Java Bean
  case class Emp(ename: String, comm: Double, deptno: Long, empno: Long,
                 hiredate: String, job: String, mgr: Long, sal: Double)

  // 变量是线程安全的, 每次操作只会作用于当前链, 不会影响其他值
  val df = spark.read.json(SparkHelper.resourcesPaths.empJson)
  df.show()

  // 引用列
  df.select($"ename", $"job").show()
  df.select('ename, 'job).show()

  // 基于已有列值新增列
  df.withColumn("upSal",$"sal"+1000).show()
  // 基于固定值新增列
  df.withColumn("intCol",lit(1000)).show()

  // 支持删除多个列
  df.drop("comm","job").show()

  df.withColumnRenamed("comm", "common").show()
}
