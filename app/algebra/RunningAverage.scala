package algebra

import domain.Candle
import domain.Tick
import scala.math.BigDecimal
import models.CandleStore

/**
 * Running average that is updated both by ticks and candles. 
 * @author fox
 */
trait RunningAverage extends CandleStoreAware with CandleListener  {

  val size: Int

  val avg: BigDecimal

  val candleStore: CandleStore

  def notify(candle: Candle) = ???

  def update(tick: Tick) = {
    val res = candleStore.stream.take(size - 1).foldLeft((1,tick.price))((cum, candle) => (cum._1 + 1, cum._2 + candle.close))
    res._2 / res._1
  }


  def update(candles: Stream[Candle]) =

  RunningAverage(size, {
      val res = candles.take(size).foldLeft((0,BigDecimal(0)))((cum,candle) => (cum._1 + 1, cum._2 + candle.close))
      res._2 / res._1
    })

}

