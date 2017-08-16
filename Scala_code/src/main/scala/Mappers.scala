import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SparkSession}


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
    * to a PrecipSource DF
    *
    * @param spark          sparkSession
    * @param sourceFilePath path to file
    * @return DataFram based on PrecipSource
    */
  def genSourceDF(spark: SparkSession, sourceFilePath: String): Dataset[PrecipSource] = {

    import spark.implicits._

    var sourceFile: RDD[String] = spark.sparkContext.textFile(sourceFilePath)

    val header = spark.sparkContext.parallelize(sourceFile.take(25))
    sourceFile = sourceFile.subtract(header)
    header.unpersist()

    var sourceDF: Dataset[PrecipSource] = sourceFile
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
      .toDS()
    //    sourceDF.show(false)

    sourceDF
  }

  /**
    * Maps a precipitation textfile to a Precipication DF
    *
    * @param spark
    * @param sourceFileContent
    */
  def precipicationDF(spark: SparkSession, sourceFileContent: RDD[(String, String)]): Unit = {

    val sources = sourceFileContent.map({ case (path, content) =>
      content.split("\n").drop(20)
        .map(s => s.split(",").map(_.trim()))
        .map(fields => Precipication(
          stationId = fields(0).toInt,
          sourceId = fields(1).toInt,
          date = fields(2),
          amount = fields(3).toInt,
          quality = fields(4).toInt
        ))
    })
    sources.count()
    //    spark.createDataset(precipitionDF)

  }

}
