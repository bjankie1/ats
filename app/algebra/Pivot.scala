package algebra

import domain.Candle
import domain.Tick

/**
 * Support line based on highest/lowest observation
 */
class Pivot extends CandleStoreAware {
  
  def update(tick: Tick, candles: Stream[Candle]) = ???
  
  def update(candles: Stream[Candle]) = ???

}

object PivotProducer {
  
  /**
   * Abalyses given candles stream and creates new pivot if there is reason for that.
   */
  def analyse( candles: Stream[Candle]): Option[Candle] = {
    ???
  }
}