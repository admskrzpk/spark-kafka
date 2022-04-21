import org.apache.spark.sql.functions.col

object SparkKafka extends App {
  import org.apache.spark.sql.SparkSession

  val spark = SparkSession
    .builder()
    .master("local[*]")
    .getOrCreate()
val message = spark
  .read
  .format("kafka")
  .option("subscribe", s"${args(0)}")
  .option("kafka.bootstrap.servers", ":9092")
  .load
  .withColumn("value", col("value").cast("String"))
  .show()

  //.show("truncate", false)
}
