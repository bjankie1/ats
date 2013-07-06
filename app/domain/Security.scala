package domain

import org.joda.time.{LocalDateTime, Period, DateTime, Interval}

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
case class Candle(open: BigDecimal, close: BigDecimal, low: BigDecimal, high: BigDecimal, volume: BigDecimal,
                  security: Security, start: LocalDateTime, interval: Period)