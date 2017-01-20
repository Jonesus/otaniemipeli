package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic

/* A special item: This is never added to the game world. It it used to show the text and play the sound in Game. */
class GameOverItem() extends Item {
  this.setImage(GameOverItem.getImage())
  this.active = false
  GameOverItem.loadSound()
}

object GameOverItem extends ItemStatic {
  imageFilename = "gfx/gameover.png"
  soundFilename = "sounds/dead.wav"
}
