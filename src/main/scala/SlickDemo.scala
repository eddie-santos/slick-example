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

}
