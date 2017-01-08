package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game

class Kivi extends Item {

  setImage(Kivi.getImage())
  Kivi.loadSound()
 
  override def processCollision() = {
    this.active = false
    Kivi.playSound()
    Game.player.health -= 1
  }
}

object Kivi extends ItemStatic {
  imageFilename = "gfx/64 kivi.png"
  soundFilename = "sounds/kivi.wav"
}
