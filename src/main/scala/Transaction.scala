/**
  * Created by Eddie on 6/9/17.
  */
import java.sql.Date

case class Transaction(id: Option[Int],
                       candidate: String,
                       contributor: String,
                       contributorState: String,
                       contributorOccupation: Option[String],
                       amount: Long,
                       date: Date
                      )
