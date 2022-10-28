package tk.gushizone.bigdata.spark.scala.sql

import tk.gushizone.bigdata.spark.scala.SparkHelper

object SparkSqlTest extends App {

  val spark = SparkHelper.sparkSession
  // 1.需要导入隐式转换(不能在 main 中使用)

  // 2.创建 case class,等价于 Java Bean
  case class Emp(ename: String, comm: Double, deptno: Long, empno: Long,
                 hiredate: String, job: String, mgr: Long, sal: Double)

  // 变量是线程安全的, 每次操作只会作用于当前链, 不会影响其他值
  val df = spark.read.json(SparkHelper.resourcesPaths.empJson)



  // 1.首先需要将 DataFrame 注册为临时视图
  df.createOrReplaceTempView("emp")

  // 2.查询员工姓名及工作
  spark.sql("SELECT ename,job FROM emp").show()

  // 3.查询工资大于 2000 的员工信息
  spark.sql("SELECT * FROM emp where sal > 2000").show()

  // 4.orderBy 按照部门编号降序，工资升序进行查询
  spark.sql("SELECT * FROM emp ORDER BY deptno DESC,sal ASC").show()

  // 5.limit  查询工资最高的 3 名员工的信息
  spark.sql("SELECT * FROM emp ORDER BY sal DESC LIMIT 3").show()

  // 6.distinct 查询所有部门编号
  spark.sql("SELECT DISTINCT(deptno) FROM emp").show()

  // 7.分组统计部门人数
  spark.sql("SELECT deptno,count(ename) FROM emp group by deptno").show()



  // 注册为全局临时视图
  df.createGlobalTempView("gemp")

  // 使用限定名称进行引用
  spark.sql("SELECT ename,job FROM global_temp.gemp").show()
}
