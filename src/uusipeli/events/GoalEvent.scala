package uusipeli.events
import uusipeli.Event
import uusipeli.Game
import uusipeli.Slice
import uusipeli.Item
import uusipeli.model._

class GoalEvent extends Event {
  delay = 6000
  
  override def start() = {
    Game.player.stopped = true
  }
  
  override def end() = {
    Game.world.slices(Game.world.slices.length-4).items(0).visible = false
    Game.player.playerAnimation = Game.player.winAnimation
    Game.showYouWin()
    Game.playYouWinSound()
    Game.addEvent(new EndGameEvent())
  }
}
