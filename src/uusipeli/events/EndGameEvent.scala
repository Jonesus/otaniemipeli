package uusipeli.events
import uusipeli.Event
import uusipeli.Game

/* End game event */
class EndGameEvent extends Event {
  delay = 3000
  
  override def start() = {
    Game.player.stopped = true    
  }
  
  override def end() = {
    Game.exitToMenuCommand = true
  }
}
