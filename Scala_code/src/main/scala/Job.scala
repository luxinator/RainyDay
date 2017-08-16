import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object Job {

  val AppName = "ECAD_JSON_Converter"
  val sparkMaster = "local[3]"
  //  val sparkMaster = "spark://node0.local:7077"
  //  val HDFSDataDir = "hdfs://node0.local:9000/ECAD_Data/"
  //  val HDFSNameNode = "hdfs://node0.local:9000"
  val HDFSDataDir = "/home/Data_KNMI/ECAD/Europe/Done/"


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName(AppName).setMaster(sparkMaster)
    val spark = SparkSession
      .builder()
      .config(conf)
      .getOrCreate()

    val sc = spark.sparkContext

    val hadoopConf = sc.hadoopConfiguration
    //    hadoopConf.set("fs.defaultFS", HDFSNameNode)

    val mapper = new Mappers()
    val sourceDF = mapper.genSourceDF(spark, HDFSDataDir + "sources.txt")
    sourceDF.show()
    //RDD with tuple(filepath, *content of file*)
    val fileList = sc.wholeTextFiles(HDFSDataDir + "RR_SOUID1000*.txt")

    mapper.precipicationDF(spark, fileList)
    //    println(precipDS.count())


    //    val precipDF = mapper.precipicationDF(, HDFSDataDir + "RR_SOUID100014.txt")

  }
}
