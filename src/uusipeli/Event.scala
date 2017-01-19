package uusipeli

/* This class represents an event, such as EndGameEvent, that is processed in the game loop.
 * 
 * Events can be safely used to change the game's state, because they are processed first in the game loop.
 * 
 * Events are run once and then they are disposed.
 */
abstract class Event {
  /* When was this event started? */
  var startTime: Long = 0
  var delay = 0
  var started = false
  var ended = false
  
  def start() = {
    /* Code to run when the event is added to the queue. */
  }
  
  def end() = {
    /* Code to run when delay has passed. */
  }
}
