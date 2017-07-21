import org.apache.spark.{SparkConf, SparkContext}
import org.apache.hadoop.fs.FileSystem


object TestScala {

  val AppName = "ECAD_JSON_Converter"
  val sparkMaster = "spark://node0:7077"
  val HDFSDataDir = "hdfs://node0:9000/ECAD_Data"

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName(AppName).setMaster(sparkMaster)
    val sc = new SparkContext(conf)
    val hadoopConf = sc.hadoopConfiguration
    hadoopConf.set("fs.defaultFS", "hdfs://node0:9000")

    val hdfs = org.apache.hadoop.fs.FileSystem.get(hadoopConf)
    val hadoopPath = new org.apache.hadoop.fs.Path(HDFSDataDir)
    val recursive = true
    val ri = hdfs.listFiles(hadoopPath, true)
    print(ri)
  }
}
