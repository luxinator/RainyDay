import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}


case class PrecipSource(sourceId: Int,
                        name: String,
                        countryCode: String,
                        latitude: String,
                        longitude: String,
                        elevation: Int,
                        elementId: String,
                        beginDate: String,
                        endDate: String,
                        participantId: Int,
                        participantName: String
                       )

case class Precipication(stationId: Int,
                         sourceId: Int,
                         date: String,
                         amount: Int,
                         quality: Int
                        )

class Mappers() {

  /**
    * Maps the textfile containing the sources.txt file in the ECAD data folder
    * to a PrecipSource object
    *
    * @param spark          sparkSession
    * @param sourceFilePath path to file
    * @return DataFram based on PrecipSource
    */
  def genSourceDF(spark: SparkSession, sourceFilePath: String): DataFrame = {

    import spark.implicits._

    var sourceFile: RDD[String] = spark.sparkContext.textFile(sourceFilePath)

    val header = spark.sparkContext.parallelize(sourceFile.take(25))
    sourceFile = sourceFile.subtract(header)

    val sourceDF = sourceFile
      .map(s => s.split(",")
        .map(_.trim()))
      .map(fields => PrecipSource(
        fields(0).toInt,
        fields(1),
        fields(2),
        fields(3),
        fields(4),
        fields(5).toInt,
        fields(6),
        fields(7),
        fields(8),
        fields(9).toInt,
        fields(10)))
      .toDF()
    sourceDF.show(false)

    sourceDF
  }


  def precipicationDF(spark: SparkSession, sourceFilPath: String): Unit = {

    val file: RDD[String] = spark.sparkContext.textFile(sourceFilPath)
    //    val header = spark.sparkCo
  }

}
