package algebra

import domain.Candle

/**
 * Listens to new candles
 */
trait CandleListener {

  def notify(candle: Candle)

}
