package tk.gushizone.bigdata.flink

object FlinkHelper {

  var resourcesPaths: ResourcesPaths = new ResourcesPaths

  class ResourcesPaths {

    var wcTxt: String = this.getClass.getClassLoader.getResource("txt/wc.txt").getPath

  }

}
