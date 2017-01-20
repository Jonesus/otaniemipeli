package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic

class YouWinItem() extends Item {
  YouWinItem.imageFilename = "gfx/youwin.png"
  this.setImage(YouWinItem.getImage())
  this.active = false
}

object YouWinItem extends ItemStatic {

}
