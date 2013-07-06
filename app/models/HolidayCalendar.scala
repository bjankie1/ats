package models

import org.joda.time.LocalDate
import scala.collection.mutable

/**
 * Holiday calendar that can serve a market session calendar open time sestimations
 */
trait HolidayCalendar {

  //TODO use ordered collection or a bitmap
  def isHoliday(day: LocalDate): Boolean

}

class SimpleHolidayCalendar(holidays: LocalDate*) extends HolidayCalendar{
  def isHoliday(day: LocalDate) = holidays.exists( _.isEqual(day))
}


trait HolidayCalendarLoader {

  def loadCalendar(id: String): Option[HolidayCalendar]

}

/**
 * Loads calendar from a flat file
 */
class FileCalendarLoader extends HolidayCalendarLoader {

  override def loadCalendar(id: String) = ???

}

/**
 * Loads a calendar from DB table
 */
class DBHolidayCalendarLoader extends HolidayCalendarLoader {

    override def loadCalendar(id: String) = ???
}

/**
 * Loader that returns a calendar where there are no holidays
 */
class NoHolidaysCalendarLoader extends HolidayCalendarLoader {

  override def loadCalendar(id: String) = {
    Some(new SimpleHolidayCalendar())
  }

}

/**
 * Component that wraps given HolidayCalendarLoader implementation
 */
trait HolidayCalendarLoaderComponent {

  val holidayCalendarLoader: HolidayCalendarLoader

  def loadCalendar(id: String) = {
    holidayCalendarLoader.loadCalendar(id)
  }

}

trait HolidayCalendarComponent {

  this: HolidayCalendarLoaderComponent =>

  val holidayCalendars: mutable.Map[String, HolidayCalendar] = mutable.HashMap()

  def holidayCalendarForMarket(id: String): Option[HolidayCalendar] = {
    holidayCalendars.get(id) match {
      case Some(calendar) => Some(calendar)
      case _ => {
        loadCalendar(id) match {
          case Some(calendar) => {
            holidayCalendars(id) = calendar
            Some(calendar)
          }
          case None => None
        }
      }
    }
  }
}