package uusipeli.effects

import uusipeli.Effect
import uusipeli.Game
import uusipeli.Player

class BeerEffect extends Effect {
  
  timeout = 800
  
  override def start(p: Player) = {
    Game.keysReversed = true
  }
  
  override def end(p: Player) = {
    Game.keysReversed = false
  }
}
