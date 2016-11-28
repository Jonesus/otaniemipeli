package otaniemipeli
import math.{sqrt, pow}

object Player {
  var health = 100
  var speed = 40
  var graphic = "player.png"
  var position = (0,0)
  
  def update() = {
    if (speed==40){
      position.(0)+= 1
      position(1)+= 1
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