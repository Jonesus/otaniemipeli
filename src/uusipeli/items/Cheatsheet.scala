package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game
import uusipeli.Player


class Cheatsheet extends Item {
  
  setImage(Cheatsheet.getImage())
  Cheatsheet.loadSound()
  
  override def processCollision() = {
    this.active = false
    this.visible = false
    Game.player.score += 5
    Cheatsheet.playSound()
  }
}

object Cheatsheet extends ItemStatic {
  imageFilename = "gfx/64 prujut.png"
  soundFilename = "sounds/prujut.wav"
}
