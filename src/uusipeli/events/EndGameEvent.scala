package uusipeli.events
import uusipeli.Event
import uusipeli.Game

class EndGameEvent extends Event {
  delay = 3000
  
  override def run() = {
    Game.stopGame()
  }
}
