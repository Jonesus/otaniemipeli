package uusipeli.events
import uusipeli.Event
import uusipeli.Game

class EndGameEvent extends Event {
  override def run() = {
    Game.stopGame()
  }
}
