package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game

class Spaghetti extends Item {

  setImage(Spaghetti.getImage())
  Spaghetti.loadSound()
 
  override def processCollision() = {
    this.active = false
    this.visible = false
    Spaghetti.playSound()
    Game.player.health += 1
  }
}

object Spaghetti extends ItemStatic {
  imageFilename = "gfx/64 spagu2.png"
  soundFilename = "sounds/spagu.wav"
}
