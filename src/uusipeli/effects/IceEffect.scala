package uusipeli.effects

import uusipeli.Effect
import uusipeli.Player

class IceEffect extends Effect {
  
  timeout = 400
  
  override def start(player: Player)={
    player.acceleration = 2
  }
  
  override def end(player: Player)={
    player.acceleration = 1
  }
}
