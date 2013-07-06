package algebra

import domain.{Gap, Candle}
import org.joda.time.Days

/**
 * Created by fox on 6/14/13.
 */
trait GapAnalyzer extends CandleStoreAware with SelectiveCandleListener {

  /**
   * Injected EventPublisher instance
   */
  val eventPublisher: EventPublisher

  def notify(candle: Candle) {
    apply(candle) match {
      case Some(gap) => eventPublisher

    }
  }

  /**
   * Processing the candle to detect if a gap can be identified. In case a new gap has been identified
   * the method will notify related services.
   *
   * @param candle The first candle of the day
   */
  def apply(candle: Candle): Option[Gap] = {
    //previous day candle
    val yesterdayCandle = candleStore.forDay(candle.start.minus(Days.days(1)).toLocalDate)

    if (candle.open > yesterdayCandle.high) {
      Some(Gap(candle.start.toLocalDate, candle.security, true, true))
    }
    else if (candle.open > yesterdayCandle.close) {
      Some(Gap(candle.start.toLocalDate, candle.security, false, true))
    }
    else if (candle.open < yesterdayCandle.low) {
      Some(Gap(candle.start.toLocalDate, candle.security, true, false))
    }
    else if (candle.open < yesterdayCandle.close) {
      Some(Gap(candle.start.toLocalDate, candle.security, false, false))
    }
    else {
      None
    }
  }

  /**
   * Expressing interest in receiving the first candle of the day.
   */
  val interestIn = CandleEventType.FIRST_OF_DAY

}
