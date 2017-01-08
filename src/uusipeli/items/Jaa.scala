package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game
import uusipeli.Player
import uusipeli.effects.IceEffect


class Jaa extends Item {
  
  setImage(Jaa.getImage())
  Jaa.loadSound()
  
  override def processCollision() = {
    this.active = false
    Jaa.playSound()
    Game.addEffect(new IceEffect())
  }
}

object Jaa extends ItemStatic {
  imageFilename = "gfx/64 jaa.png"
  soundFilename = "sounds/liukastus.wav"
}
