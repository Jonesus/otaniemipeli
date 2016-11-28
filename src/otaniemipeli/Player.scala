package otaniemipeli
import math.{sqrt, pow}

object Player {
  var health = 100
  var speed = 40
  var graphic = "player.png"
  var position_x = Viewport.x/2
  var position_y = 0
  
  def update() = {
    if (speed == 40){
      position_y += 1
      //Listens to user and moves accordingly.
      if (keypressed){
        
      }
    }
  }
  
  def render() = {
    
  }
  
  def checkForCollisions(World.worldItemList) = {
    var collisionList = []
    for item in World.worldItemList{
      if sqrt(pow(player.x-item.x,2) + pow(player.y-item.y,2)) < 50{
        collisionList += item
      }
    }
    return collisionList
  }
  
}