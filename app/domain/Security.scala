package domain

import org.joda.time.DateTime
import org.joda.time.Interval

/**
 * Security traded on SE
 */
case class Security( name: String, symbol: String)

/**
 * Security trading information at given time.
 */
case class Tick(price: BigDecimal, volumne: Int, when: DateTime, security: Security)

/**
 * Aggregation of candles
 */
case class Candle(open: BigDecimal, close: BigDecimal, low: BigDecimal, high: BigDecimal, interval: Int)