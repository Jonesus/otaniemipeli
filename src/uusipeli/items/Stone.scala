package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game

class Stone extends Item {

  setImage(Stone.getImage())
  Stone.loadSound()
 
  override def processCollision() = {
    this.active = false
    this.visible = false
    Stone.playSound()
    Game.player.health -= 1
  }
}

object Stone extends ItemStatic {
  imageFilename = "gfx/64 kivi.png"
  soundFilename = "sounds/kivi.wav"
}
