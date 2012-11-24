package algebra

import domain.Candle
import domain.Tick
import scala.math.BigDecimal

/**
 * Running average that is updated both by ticks and candles. 
 * @author fox
 */
class RunningAverage(size: Int, avg: BigDecimal) extends Metric {
  
  def update(tick: Tick, candles: Stream[Candle]) =
    RunningAverage(size, {
      val res = candles.take(size - 1).foldLeft((1,tick.price))((cum,candle) => (cum._1 + 1, cum._2 + candle.close))
      res._2 / res._1
    })
  
  def update(candles: Stream[Candle]) = 
    RunningAverage(size, {
      val res = candles.take(size).foldLeft((0,BigDecimal(0)))((cum,candle) => (cum._1 + 1, cum._2 + candle.close))
      res._2 / res._1
    })
    
 
  val value = avg
}

object RunningAverage {
  private def apply(size: Int, value: BigDecimal): RunningAverage = new RunningAverage(size, value)
  
  def apply(size: Int): RunningAverage = new RunningAverage(size, BigDecimal(0))
}
