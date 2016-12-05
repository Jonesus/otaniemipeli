package uusipeli

import java.awt.image.BufferedImage
import java.awt.Color



class Player {
  
  var position_x = 0.0
  var position_y = 0.0
  var deltaX = 0  // Speed vectors for current frame
  var deltaY = 0
  var xVelocity = 0  // Speed vectors for current movement
  var yVelocity = 0
  val maxSpeedX = 10
  val maxSpeedY = 5
  val acceleration = 1
  
  val Y_RESTING_SPEED = 2  // Constant speed down
  
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
    
    /* Checks if the player is accelerating or decelerating
     * and changes current frame delta vectors accordingly
     */
    if (deltaX == 0 && xVelocity != 0) {
      deltaX = if (xVelocity > 0) 0 - acceleration else acceleration
    }
    if (deltaY == 0 && yVelocity != Y_RESTING_SPEED) {
      deltaY = if (yVelocity > Y_RESTING_SPEED) 0 - acceleration else acceleration
    }
    /* Calculates new velocity vectors while keeping max
     * velocity in control
     */
    if (scala.math.abs(xVelocity + deltaX) < maxSpeedX) {
      xVelocity += deltaX
    }
    if (scala.math.abs(yVelocity + deltaY) < maxSpeedY) {
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

