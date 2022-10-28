package tk.gushizone.bigdata.spark.scala.sql

import tk.gushizone.bigdata.spark.scala.SparkHelper
import org.apache.spark.sql.functions._

object StructuredApiTest extends App {

  val spark = SparkHelper.sparkSession
  // 1.需要导入隐式转换(不能在 main 中使用)
  import spark.implicits._


  // 2.创建 case class,等价于 Java Bean
  case class Emp(ename: String, comm: Double, deptno: Long, empno: Long,
                 hiredate: String, job: String, mgr: Long, sal: Double)

  // 变量是线程安全的, 每次操作只会作用于当前链, 不会影响其他值
  val df = spark.read.json(SparkHelper.resourcesPaths.empJson)
  df.show()


  // 1.查询员工姓名及工作
  df.select($"ename", $"job").show()

  // 2.filter 查询工资大于 2000 的员工信息
  df.filter($"sal" > 2000).show()

  // 3.orderBy 按照部门编号降序，工资升序进行查询
  df.orderBy(desc("deptno"), asc("sal")).show()

  // 4.limit 查询工资最高的 3 名员工的信息
  df.orderBy(desc("sal")).limit(3).show()

  // 5.distinct 查询所有部门编号
  df.select("deptno").distinct().show()

  // 6.groupBy 分组统计部门人数
  df.groupBy("deptno").count().show()


}
