package tk.gushizone.bigdata.spark.scala.sql

import org.apache.spark.sql.SparkSession

object StructuredApi  {

  val empPath: String = this.getClass.getClassLoader.getResource("emp.json").getPath

  def main(args: Array[String]){

    val spark = SparkSession.builder()
      .appName("Spark-SQL")
      .master("local[1]")
      .getOrCreate()
    val df = spark.read.json(empPath)
    df.show()

    // 1.需要导入隐式转换
    import spark.implicits._


  }

}
