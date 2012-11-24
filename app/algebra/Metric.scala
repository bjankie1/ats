package algebra

import play.libs.Akka
import domain.Tick
import domain.Candle

/**
 * Metric base.
 */
trait Metric {
  
  /**
   * Metric updated with recent Tick and given candles stream
   */
  def update(tick: Tick, candles: Stream[Candle]): Metric
  
  /**
   * Metric updated with recent candle and given candles stream
   */
  def update(candles: Stream[Candle]): Metric
}