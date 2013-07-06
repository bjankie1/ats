package models

import org.joda.time.{DateTimeZone, LocalTime, DateTime, LocalDate}
import scala.collection.mutable

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
  def sessionEnd(day: LocalDate): DateTime

  /**
   * Returns time a session at given day started
   * @param day A business day
   * @return Returns exact time
   * @throws IllegalArgumentException when given day is not a business day
   */
  def sessionStart(day: LocalDate): DateTime

  /**
   * Start of the session after given date.
   * @param day Date to calculate next session starting date from
   * @return Session start time and date.
   */
  def nextSessionStart(day: LocalDate): DateTime
}


/**
 * Basic implementation taking all required parameters in construct.
 * @param start Time when session starts
 * @param end Time the session usually ends
 * @param tz Timezone default for this calendar
 * @param holidayCalendar Calendar of holidays
 */
class SimpleSessionCalendar(start: LocalTime, end: LocalTime, tz: DateTimeZone,
                            holidayCalendar: HolidayCalendar) extends SessionCalendar {

  def sessionEnd(day: LocalDate): DateTime = day.toDateTime(end, tz)

  def sessionStart(day: LocalDate): DateTime = day.toDateTime(start, tz)

  def nextSessionStart(day: LocalDate): DateTime = holidayCalendar.isHoliday(day.plusDays(1)) match {
    case true => nextSessionStart(day.plusDays(1))
    case false => day.toDateTime(start, tz)
  }

}

/**
 * Loading service definition
 */
trait SessionCalendarLoader {

  def loadCalendar(id: String): Option[SessionCalendar]

}

trait SessionCalendarLoaderComponent {

  val sessionCalendarLoader: SessionCalendarLoader

  def loadSessionCalendar(id: String): Option[SessionCalendar] = {
    sessionCalendarLoader.loadCalendar(id)
  }

}

/**
 * Session calendar object managing calendars.
 */
trait SessionCalendarService {

  this: SessionCalendarLoaderComponent =>

  val sessionCalendars: mutable.Map[String, SessionCalendar] = mutable.HashMap()

  /**
   * Session calendar for given market.
   *
   * @param market Market identifier
   */
  def sessionCalendar(market: String): Option[SessionCalendar] = {
    sessionCalendars.get(market) match {
      case Some(calendar) => Some(calendar)
      case None => {
        loadSessionCalendar(market) match {
          case Some(calendar) => {
            sessionCalendars(market) = calendar
            Some(calendar)
          }
          case None => None
        }
      }
    }

  }

}