package domain

import org.joda.time.LocalTime

/**
 * Market identification
 *
 * Created by fox on 5/25/13.
 */
case class Market( id: String, name: String, region: Region.Region)


/**
 * Geographical region
 */
object Region extends Enumeration {

  type Region = Value

  val AMERICAS, EMEA, ASIA, AFRICA = Value
}