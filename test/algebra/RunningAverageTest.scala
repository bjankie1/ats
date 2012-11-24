package algebra

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.scalatest.GivenWhenThen
import org.scalatest.FunSpec
import domain.Candle
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import domain.Tick
import org.joda.time.DateTime
import domain.Security

//@RunWith(classOf[JUnitRunner])
class RunningAverageTest extends FunSpec with GivenWhenThen with ShouldMatchers {
  describe("A RunningAverage") {
    
    it("Should return close value for single candle") {
      given("a candle")
      val candle = Candle(0, 101, 0, 0, 600)
      
      when("instantiate average")
      val average = RunningAverage(10).update(Stream(candle))
      
      then("average should be equals to candle close value")
      average.value should equal(candle.close)
    }
    
    
    it("Should calculate average for multiple candles") {
      given("a candles")
      val candle1 = Candle(0, 10, 0, 0, 600)
      val candle2 = Candle(0, 12, 0, 0, 600)
      
      when("instantiate average")
      val average = RunningAverage(10).update(Stream(candle1, candle2))
      
      then("average should be equals to candle close value")
      average.value should equal(BigDecimal((10 + 12)/2))
    }
    
    
    it("Should calculate average for multiple candles with tick") {
      given("a candles")
      val candle1 = Candle(0, 10, 0, 0, 600)
      val candle2 = Candle(0, 12, 0, 0, 600)
      val tick = Tick(13, 1, DateTime.now, Security("test", "test"))
      
      when("instantiate average")
      val average = RunningAverage(10).update(tick, Stream(candle1, candle2))
      
      then("average should be equals to candle close value")
      average.value should equal(BigDecimal(10 + 12 + 13)/3)
    }
    
    
  }
}
