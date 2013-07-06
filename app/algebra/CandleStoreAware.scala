package algebra

import play.libs.Akka
import domain.Tick
import domain.Candle
import models.CandleStore

/**
 * CandleStoreAware base.
 */
trait CandleStoreAware {

  val candleStore: CandleStore


}