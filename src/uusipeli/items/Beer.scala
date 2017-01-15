package uusipeli.items

import uusipeli.effects.BeerEffect
import uusipeli.Game
import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Player

class Beer extends Item {
  
  setImage(Beer.getImage())
  Beer.loadSound()
  
  override def processCollision() = {
    this.active = false
    this.visible = false
    Beer.playSound()
    Game.addEffect(new BeerEffect())
  }
}

object Beer extends ItemStatic {
  imageFilename = "gfx/64 kalja.png"
  soundFilename = "sounds/olutpullo.wav"
}
