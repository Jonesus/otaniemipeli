package uusipeli.events
import uusipeli.Event
import uusipeli.Game
import uusipeli.Slice
import uusipeli.Item
import uusipeli.model._

class PlayerDeadEvent extends Event {
  delay = 6000
  
  override def start() = {
    Game.player.stopped = true
  }
  
  override def end() = {
    Game.world.slices(Game.world.slices.length-4).items(0).visible = false
    Game.player.playerAnimation = Game.player.deadPlayerAnimation
    Game.showGameOver()
    Game.addEvent(new EndGameEvent())
  }
}
