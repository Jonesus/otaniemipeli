package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game
import uusipeli.Player


class Sausage extends Item {
  
  setImage(Sausage.getImage())
  Sausage.loadSound()
  
  override def processCollision() = {
    this.active = false
    this.visible = false
    Game.player.score -= 5
    Sausage.playSound()
  }
}

object Sausage extends ItemStatic {
  imageFilename = "gfx/64 nakki.png"
  soundFilename = "sounds/nakki.wav"
}
