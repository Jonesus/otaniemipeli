package uusipeli.effects

import uusipeli.Effect
import uusipeli.Game

class BeerEffect extends Effect {
  
  delay = 4000
  
  override def start() = {
    Game.keysReversed = true
    Game.player.playerIsDrunk()

  }
  
  override def end() = {
    Game.keysReversed = false
    Game.player.playerIsSober()
  }
}
