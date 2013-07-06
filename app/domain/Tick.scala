package domain

import org.joda.time.DateTime

/**
 * Tick is the smallest unit of information related to security trading. It represents amount of securities
 * traded at given time and at given price
 *
 * Created by fox on 6/12/13.
 */
case class Tick( security: Security, time: DateTime, price: BigDecimal, volume: Int)