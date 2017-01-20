package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic

class GameOverItem() extends Item {
  GameOverItem.imageFilename = "gfx/gameover.png"
  this.setImage(GameOverItem.getImage())
  this.active = false
}

object GameOverItem extends ItemStatic {

}
