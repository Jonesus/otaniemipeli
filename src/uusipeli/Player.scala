package uusipeli

import java.awt.image.BufferedImage
import java.awt.Color

class Player {
  
  var position_x = 0.0
  var position_y = 0.0
  
  var default_speed = 0.8
  
  /* Player speed: Pixels per frame. */
  var speed_x = 0.0
  var speed_y = 0.0
  
  var width = 40
  var height = 40
  
  var image = drawImage()
  
  def turnUp() = {
    speed_x = 0
    speed_y = -default_speed
  }
  
  def turnDown() = {
    speed_x = 0
    speed_y = default_speed
  }
  
  def turnLeft() = {
    speed_x = -default_speed
    speed_y = 0
  }
  
  def turnRight() = {
    speed_x = default_speed
    speed_y = 0
  }  
  
  def render: BufferedImage = {
    return image
  }
  
  def update() = {
    
    /* Here we update the player's position. */
    position_x += speed_x
    position_y += speed_y
  }
  
  def drawImage(): BufferedImage = {
    val avatar = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val g = avatar.createGraphics()
    g.setColor(Color.red)
    g.fillOval(0, 0, 40, 40)
    
    return avatar
  }
}

