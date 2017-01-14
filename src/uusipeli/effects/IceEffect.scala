package uusipeli.effects

import uusipeli.Effect
import uusipeli.Game
import uusipeli.model._

class IceEffect extends Effect {
  
  timeout = 4000
  val ratio = 4
  
  override def start() = {
    Game.player.Y_RESTING_SPEED = PLAYER_SPEED_DOWN * ratio
  }
  
  override def end() = {
    Game.player.Y_RESTING_SPEED = PLAYER_SPEED_DOWN
  }
}
