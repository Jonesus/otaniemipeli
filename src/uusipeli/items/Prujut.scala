package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game
import uusipeli.Player


class Prujut extends Item {
  
  setImage(Prujut.getImage())
  Prujut.loadSound()
  
  override def processCollision() = {
    this.active = false
    this.visible = false
    Game.player.score += 5
    Prujut.playSound()
  }
}

object Prujut extends ItemStatic {
  imageFilename = "gfx/64 prujut.png"
  soundFilename = "sounds/prujut.wav"
}
