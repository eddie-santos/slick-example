/**
  * Created by Eddie on 6/9/17.
  */
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object SlickDemo {

  val db: Database = Database.forConfig("pgConfig")

  def createTable: Unit = {
    val setup = DBIO.seq(
      Tables.transactions.schema.drop,
      Tables.transactions.schema.create
    )
    val setupFuture = db.run(setup)
    Await.result(setupFuture, Duration.Inf)
  }

  def insertFecData: Unit = {
    val fecData = FECData.loadOhio
    val transactions: Iterator[Transaction] = fecData.transactions

    val insert = DBIO.seq(
      Tables.transactions ++= transactions.toSeq
    )
    val insertFuture = db.run(insert)
    Await.result(insertFuture, Duration.Inf)
  }

  def queryData: Seq[Transaction] = {
    val action = Tables.transactions.result
    val result: Future[Seq[Transaction]] = db.run(action)
    Await.result(result, Duration.Inf)
  }

  def queryTotalDonations: Map[String, Long] = {
    val grouped = Tables.transactions.groupBy(_.candidate)
    val aggregated = grouped.map {
      case (candidate, group) => (candidate -> group.map(_.amount).sum)
    }.result
    val result = db.run(aggregated)
    Await.result(result, Duration.Inf).toMap.mapValues { _.getOrElse(0L) }
  }

  def main(args: Array[String]): Unit = {
    createTable
    insertFecData
    queryData.take(5).foreach(println)
    queryTotalDonations.foreach(println)
  }

}
