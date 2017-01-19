package uusipeli.events
import uusipeli.Event
import uusipeli.Game

class EndGameEvent extends Event {
  delay = 4000
  
  override def start() = {
    Game.player.stopped = true    
  }
  
  override def end() = {
    Game.exitToMenuCommand = true
  }
}
