/**
  * Created by Eddie on 6/9/17.
  */
import java.io.File
import java.sql.Date
import java.text.SimpleDateFormat
import com.github.tototoshi.csv._

class FECData(val transactions: Iterator[Transaction])

object FECData {
  val dataDirectory = "src/main/resources/data"

  private def load(fileName: String): FECData = {
    val reader = CSVReader.open(new File(dataDirectory + fileName))
    val transactions = for {
      row <- reader.iteratorWithHeaders
      id = None
      candidate = row("candidate")
      contributor = row("contributor_name")
      state = row("contributor_state")
      occupation = row("contributor_occupation") match {
        case "" => None
        case v => Some(v)
      }
      amount = (row("Aamount").toDouble*100).toInt
      date = new Date(dateParser.parse(row("date")).getTime)
    } yield Transaction(id, candidate, contributor, state, occupation, amount, date)

    new FECData(transactions)
  }

  def loadAll: FECData = load("us.csv")
  def loadOhio: FECData = load("ohio.csv")

}

