package tk.gushizone.bigdata.spark.scala.sql

import tk.gushizone.bigdata.spark.scala.SparkHelper

object DatasetTest extends App {

  val spark = SparkHelper.sparkSession
  // 1.需要导入隐式转换(不能在 main 中使用)
  import spark.implicits._


  // 2.创建 case class,等价于 Java Bean
  case class Emp(ename: String, comm: Double, deptno: Long, empno: Long,
                 hiredate: String, job: String, mgr: Long, sal: Double)

  // 3.1.由外部数据集创建 Datasets
  val ds = spark.read.json(SparkHelper.resourcesPaths.empJson).as[Emp]
  ds.show()

  // 3.2.由内部数据集创建 Datasets
  val caseClassDS = Seq(Emp("ALLEN", 300.0, 30, 7499, "1981-02-20 00:00:00", "SALESMAN", 7698, 1600.0),
    Emp("JONES", 300.0, 30, 7499, "1981-02-20 00:00:00", "SALESMAN", 7698, 1600.0))
    .toDS()
  caseClassDS.show()

}
