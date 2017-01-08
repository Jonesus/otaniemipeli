package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game
import uusipeli.Player


class Jaa extends Item {
  
  setImage(Jaa.getImage())
  Jaa.loadSound()
  
  override def processCollision() = {
    this.active = false
    Jaa.playSound()
  }
}

object Jaa extends ItemStatic {
  imageFilename = "gfx/64 jaa.png"
  soundFilename = "sounds/liukastus.wav"
}
