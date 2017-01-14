package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game

class Spagetti extends Item {

  setImage(Spagetti.getImage())
  Spagetti.loadSound()
 
  override def processCollision() = {
    this.active = false
    this.visible = false
    Spagetti.playSound()
    Game.player.health += 1
  }
}

object Spagetti extends ItemStatic {
  imageFilename = "gfx/64 spagu2.png"
  soundFilename = "sounds/spagu.wav"
}
