package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game

class LevelGoalItem(val filename: String) extends Item {
  LevelGoalItem.imageFilename = filename
  this.setImage(LevelGoalItem.getImage())
  
  override def processCollision() = {
    Game.stopGame()
  }
}

object LevelGoalItem extends ItemStatic {

}
