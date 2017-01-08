package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game
import uusipeli.Player


class Noppa extends Item {
  
  setImage(Noppa.getImage())
  Noppa.loadSound()
  
  override def processCollision() = {
    this.active = false
    Game.player.score += 1
    Noppa.playSound()
  }
}

object Noppa extends ItemStatic {
  imageFilename = "gfx/64 noppa.png"
  soundFilename = "sounds/noppa.wav"
}
