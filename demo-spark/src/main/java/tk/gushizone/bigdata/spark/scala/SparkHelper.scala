package tk.gushizone.bigdata.spark.scala

import org.apache.spark.sql.SparkSession

object SparkHelper {

  var sparkSession: SparkSession = SparkSession.builder()
    .appName("Spark-SQL")
    .master("local[2]")
    .getOrCreate()

  var resourcesPaths: ResourcesPaths = new ResourcesPaths

  class ResourcesPaths {

    var empTxt: String = this.getClass.getClassLoader.getResource("txt/emp.txt").getPath
    var wcTxt: String = this.getClass.getClassLoader.getResource("txt/wc.txt").getPath

    val empJson: String = this.getClass.getClassLoader.getResource("json/emp.json").getPath
  }

}
