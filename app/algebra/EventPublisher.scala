package algebra

/**
 * Publishes market events resulting from data processing.
 *
 * Created by fox on 6/27/13.
 */
trait EventPublisher {

  /**
   * Receive an event that needs to be published. Published event can be of any type.
   * @param event Event object to publish.
   */
  def publish(event: Any)

}
