package uusipeli.effects

import uusipeli.Effect
import uusipeli.Game
import uusipeli.Player

class BeerEffect extends Effect {
  
  timeout = 4000
  
  override def start() = {
    Game.keysReversed = true
    // println("BeerEffect started.")
  }
  
  override def end() = {
    Game.keysReversed = false
    // println("BeerEffect ended.")
  }
}
