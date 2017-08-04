import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object Job {

  val AppName = "ECAD_JSON_Converter"
  val sparkMaster = "local[2]"
  //  val sparkMaster = "spark://node0.local:7077"
  val HDFSDataDir = "hdfs://node0.local:9000/ECAD_Data/"
  val HDFSNameNode = "hdfs://node0.local:9000"

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName(AppName).setMaster(sparkMaster)
    val spark = SparkSession
      .builder()
      .config(conf)
      .getOrCreate()

    val sc = spark.sparkContext

    val hadoopConf = sc.hadoopConfiguration
    hadoopConf.set("fs.defaultFS", HDFSNameNode)

    val mapper = new Mappers()
    val sourceDF = mapper.genSourceDF(spark, HDFSDataDir + "sources.txt")

  }
}
