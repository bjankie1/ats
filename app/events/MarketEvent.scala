package events

import domain.Tick
import org.joda.time.DateTime

/**
 * Base event class shared between all market data events.
 *
 * Created by fox on 5/25/13.
 */
abstract class MarketEvent {

}

case class SessionEnding(date: DateTime)