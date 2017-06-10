/**
  * Created by Eddie on 6/9/17.
  */
import java.sql.Date
import slick.jdbc.PostgresProfile.api._

object Tables {

  class Transactions(tag: Tag) extends Table[Transaction](tag, "transactions") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def candidate = column[String]("candidate")
    def contributor = column[String]("contributor")
    def contributorState = column[String]("contributor_state")
    def contributorOccupation = column[Option[String]]("contributor_occupation")
    def amount = column[Long]("amount")
    def date = column[Date]("date")

    def * = (id.?, candidate, contributor, contributorState, contributorOccupation,
      amount, date) <> (Transaction.tupled, Transaction.unapply)
  }

  val transactions = TableQuery[Transactions]

}
