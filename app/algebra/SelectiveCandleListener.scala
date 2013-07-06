package algebra

/**
 * Listens to selected candles. Type of candles defined by an enumeration
 * Created by fox on 6/19/13.
 */
trait SelectiveCandleListener extends CandleListener {

  /**
   * Filter applied to candle events.
   * @return Type of candle event.
   */
  def interestIn: CandleEventType.CandleEventType

}

object CandleEventType extends Enumeration {

  type CandleEventType = Value

  val FIRST_OF_DAY, LAST_OF_DAY = Value

}
