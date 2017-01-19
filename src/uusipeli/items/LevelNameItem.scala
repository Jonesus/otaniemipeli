package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic

class LevelNameItem(val filename: String) extends Item {
  LevelNameItem.imageFilename = filename
  LevelNameItem.loadImage()
  this.setImage(LevelNameItem.getImage())
  this.active = false
}

object LevelNameItem extends ItemStatic {

}
