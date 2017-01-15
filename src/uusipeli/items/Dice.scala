package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game
import uusipeli.Player


class Dice extends Item {
  
  setImage(Dice.getImage())
  Dice.loadSound()
  
  override def processCollision() = {
    this.active = false
    this.visible = false
    Game.player.score += 1
    Dice.playSound()
  }
}

object Dice extends ItemStatic {
  imageFilename = "gfx/64 noppa.png"
  soundFilename = "sounds/noppa.wav"
}
