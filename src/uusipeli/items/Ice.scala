package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game
import uusipeli.Player
import uusipeli.effects.IceEffect


class Ice extends Item {
  
  setImage(Ice.getImage())
  Ice.loadSound()
  
  override def processCollision() = {
    // Process collision if this item is not disabled temporarily.
    if (this.checkIfDisabledAndEnable()) {
      Ice.playSound()
      Game.addEffect(new IceEffect())
      // Disable for one second.
      this.disableTemporarily(1000)
    }
  }
}

object Ice extends ItemStatic {
  imageFilename = "gfx/64 jaa.png"
  soundFilename = "sounds/liukastus.wav"
}
