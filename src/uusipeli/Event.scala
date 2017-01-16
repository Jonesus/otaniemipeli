package uusipeli

/* This class represents an event, such as EndGameEvent, that is processed in the game loop.
 * 
 * Events can be used to safely change the game's state, as they are processed first in the game loop.
 * 
 * Events are run exactly once and then they are disposed.
 */
class Event {
  def run() = {
    // Code to run
  }
}
