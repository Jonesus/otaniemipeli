package uusipeli.effects

import uusipeli.Effect
import uusipeli.Game

class IceEffect extends Effect {
  
  timeout = 4000
  val ratio = 4
  
  override def start() = {
    Game.player.Y_RESTING_SPEED = Game.player.Y_RESTING_SPEED * ratio
  }
  
  override def end() = {
    Game.player.Y_RESTING_SPEED = Game.player.Y_RESTING_SPEED / ratio
  }
}
