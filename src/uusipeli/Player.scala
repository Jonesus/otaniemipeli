package uusipeli

import java.awt.image.BufferedImage
import java.awt.Color

class Player {
  
  var position_x = 0.0
  var position_y = 0.0
  var deltaX = 0
  var deltaY = 0
  var xVelocity = 0
  var yVelocity = 0
  var maxSpeed = 20
  var acceleration = 1
  
  var default_speed = 0.8
  
  /* Player speed: Pixels per frame. */
  var speed_x = 0.0
  var speed_y = 0.0
  
  var width = 40
  var height = 40
  
  var image = drawImage()
  
  def turnUp() = {
    deltaY -= acceleration
  }
  
  def turnDown() = {
    deltaY += acceleration
  }
  
  def turnLeft() = {
    deltaX -= acceleration
  }
  
  def turnRight() = {
    deltaX += acceleration
  }
  

  def render: BufferedImage = {
    return image
  }
  
  def move(dx: Int, dy: Int) = {
    position_x += dx
    position_y += dy
  }
  
  def update() = {
    
    /* Here we update the player's position. */
    if (deltaX == 0 && xVelocity != 0) {
      deltaX = if (xVelocity > 0) 0 - acceleration else acceleration
    }
    if (deltaY == 0 && yVelocity != 0) {
      deltaY = if (yVelocity > 0) 0 - acceleration else acceleration
    }
    
    if (scala.math.abs(xVelocity + deltaX) < maxSpeed) {
      xVelocity += deltaX
    }
    if (scala.math.abs(yVelocity + deltaY) < maxSpeed) {
      yVelocity += deltaY
    }
    
    move(xVelocity, yVelocity)
    deltaX = 0
    deltaY = 0
  }
  
  def drawImage(): BufferedImage = {
    val avatar = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val g = avatar.createGraphics()
    g.setColor(Color.red)
    g.fillOval(0, 0, 40, 40)
    
    return avatar
  }
}

