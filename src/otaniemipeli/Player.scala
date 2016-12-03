package otaniemipeli
import math.{sqrt, pow}
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

object Player {
  var health = 100
  var speed = 40
  var verticalspeed = 0
  var graphic = "../gfx/teekkari.png"
  var position_x = Viewport.x/2
  var position_y = Viewport.y/4
  var img = ImageIO.read(new File("player.jpg"))
  
  def update() = {
    if (speed == 40){
      position_y += 1}
    if (speed>40){
      position_y+=2
    }
    if (verticalspeed<0){
      position_x+=1  
      }
    if (verticalspeed>0){
      position_x-=1
    }
  }
  
  def render(img: BufferedImage) = {
    return img
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