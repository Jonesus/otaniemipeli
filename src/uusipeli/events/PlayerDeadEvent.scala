package uusipeli.events
import uusipeli.Event
import uusipeli.Game
import uusipeli.Slice
import uusipeli.Item
import uusipeli.model._

/* Player is dead event. */
class PlayerDeadEvent extends Event {
  delay = 500
  
  override def start() = {
    Game.player.stopped = true
  }
  
  override def end() = {
    Game.world.slices(Game.world.slices.length-4).items(0).visible = false
    Game.player.playerAnimation = Game.player.deadPlayerAnimation
    Game.showGameOver()
    Game.playGameOverSound()
    Game.addEvent(new EndGameEvent())
  }
}
