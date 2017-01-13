package uusipeli.items

import uusipeli.effects.BeerEffect
import uusipeli.Game
import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Player

class Olutpullo extends Item {
  
  setImage(Olutpullo.getImage())
  Olutpullo.loadSound()
  
  override def processCollision() = {
    this.active = false
    this.visible = false
    Olutpullo.playSound()
    Game.addEffect(new BeerEffect())
  }
}

object Olutpullo extends ItemStatic {
  imageFilename = "gfx/64 kalja.png"
  soundFilename = "sounds/olutpullo.wav"
}
