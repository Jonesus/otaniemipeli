package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game
import uusipeli.Player


class Nakki extends Item {
  
  setImage(Nakki.getImage())
  Nakki.loadSound()
  
  override def processCollision() = {
    this.active = false
    this.visible = false
    Game.player.score -= 5
    Nakki.playSound()
  }
}

object Nakki extends ItemStatic {
  imageFilename = "gfx/64 nakki.png"
  soundFilename = "sounds/nakki.wav"
}
