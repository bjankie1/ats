package domain

import org.joda.time.{DateTime, LocalDate}

/**
 * Tracks sessions start and end times
 *
 * Created by fox on 5/25/13.
 */
trait SessionCalendar {

  /**
   * Returns time specific to given market timezone when session is planned to finish
   * @param day Date agnostic to given timezone
   * @return Session start time and date.
   */
  def sessionEnd( day: LocalDate) : DateTime

  /**
   * Returns time a session at given day started
   * @param day A business day
   * @return Returns exact time
   * @throws IllegalArgumentException when given day is not a business day
   */
  def sessionStart( day: LocalDate) : DateTime

  /**
   * Start of the session after given date.
   * @param day Date to calculate next session starting date from
   * @return Session start time and date.
   */
  def nextSessionStart( day: LocalDate) :DateTime
}
