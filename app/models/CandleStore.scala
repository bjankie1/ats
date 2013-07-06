package models

import domain.Candle
import org.joda.time.LocalDate

/**
 * Access to candles related to given security
 * Created by fox on 6/14/13.
 */
trait CandleStore {

  /**
   * Stream of candles with head pointing to the most recent one.
   * @return Returns stream of candles
   */
  def stream: Stream[Candle]


  /**
   * Candle previous to given by parameter
   * @param candle Base candle
   * @return Previous candle
   */
  def previous(candle: Candle): Candle

  /**
   * Produces candle for given day. If day is not working day the list will be empty.
   * @param day Day to return candle for
   */
  def forDay(day: LocalDate): Candle

  /**
   * Same as #forDay method but looks up for previous day to find the last business day if
   * one given by parameter was off
   * @param day Day to look candles for
   * @return Returns list of candles
   */
  def forBusinessDay(day: LocalDate): List[Candle]

}
