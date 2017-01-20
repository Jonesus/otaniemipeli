package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic

/* A special item: This is never added to the game world. It it used to show the text and play the sound in Game. */
class YouWinItem() extends Item {
  this.setImage(YouWinItem.getImage())
  this.active = false
  YouWinItem.loadSound()
}

object YouWinItem extends ItemStatic {
  imageFilename = "gfx/youwin.png"
  soundFilename = "sounds/win.wav"
}
