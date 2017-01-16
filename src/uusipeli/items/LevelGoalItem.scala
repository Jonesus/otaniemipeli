package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game
import uusipeli.Event
import uusipeli.events.EndGameEvent

class LevelGoalItem(val filename: String) extends Item {
  LevelGoalItem.imageFilename = filename
  this.setImage(LevelGoalItem.getImage())
  
  override def processCollision() = {
    Game.addEvent(new EndGameEvent())
  }
}

object LevelGoalItem extends ItemStatic {

}
