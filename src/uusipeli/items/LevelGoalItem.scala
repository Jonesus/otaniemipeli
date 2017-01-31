package uusipeli.items

import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Game
import uusipeli.Event
import uusipeli.events.EndGameEvent
import uusipeli.events.GoalEvent

class LevelGoalItem(val filename: String) extends Item {
  LevelGoalItem.imageFilename = filename
  LevelGoalItem.loadImage()
  this.setImage(LevelGoalItem.getImage())
  
  override def processCollision() = {
    this.active = false
    Game.addEvent(new GoalEvent)
  }
}

object LevelGoalItem extends ItemStatic {

}
