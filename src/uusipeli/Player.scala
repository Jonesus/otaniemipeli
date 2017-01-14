package uusipeli

import java.awt.image.BufferedImage
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File
import scala.collection.mutable.ArrayBuffer
import uusipeli.model._

class Player {
  
  var position_x = 0.0
  var position_y = 0.0
  var deltaX = 0  // Speed vectors for current frame
  var deltaY = 0
  var xVelocity = 0  // Speed vectors for current movement
  var yVelocity = 0
  var health = 5
  var score = 0
  
  val maxSpeedX = 10
  val maxSpeedY = 7
  var acceleration = 1
  
  var Y_RESTING_SPEED = 2  // Constant speed down
  
  var width = 128
  var height = 128
  
  /* Player walking animation */
  val playerAnimation = new Animation
  var avatar_filename_right = "gfx/128 pixel teekkari oikea.png"
  var avatar_filename_left = "gfx/128 pixel teekkari vasen.png"
  
  loadResources()

  def loadResources() = {
    /* Load the animation for the player avatar. */
    this.playerAnimation.frameDuration = 500
    this.playerAnimation.addFrame(avatar_filename_left)
    this.playerAnimation.addFrame(avatar_filename_right)
  }
  
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
    return this.playerAnimation.render()
  }
  
  def move(dx: Int, dy: Int) = {
    position_x += dx
    position_y += dy
  }
  
  
  def update() = {
    updatePosition()
    
  }
  
  
  def intersects(player: Player, item: Item) : Boolean = {
    // Here we assume that the slice is as wide as the world.
    val itemX = item.position_x
    val itemY = item.position_y + (item.slice_index * SLICE_HEIGHT)
    
    val playerX = player.position_x
    val playerY = player.position_y
    
    if (itemX < playerX + player.width && itemX + item.width > playerX &&
        itemY < playerY + player.height && itemY + item.height > playerY) {
      return true
    }
    return false
  }
  
  
  def checkCollisions(items: ArrayBuffer[Item]) = {
    for (item <- items) {
      if (intersects(this, item) && item.active == true) {
        item.processCollision()
      }
    }
  }
  
  def updatePosition() = {
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
    
    if ((position_x - (width / 2)) < WALL_WIDTH) {
      xVelocity = 0
      position_x = WALL_WIDTH + (width / 2)
    }
    if ((position_x + (width / 2)) > (SLICE_WIDTH - WALL_WIDTH)) {
      xVelocity = 0
      position_x = SLICE_WIDTH - WALL_WIDTH - (width / 2)
    }
  }
  
  
  /* Not in use any more. */
  def drawImage(): BufferedImage = {
    val avatar = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val g = avatar.createGraphics()
    g.setColor(Color.red)
    g.fillOval(0, 0, 40, 40)
    
    return avatar
  }
  
  def reset() {
    position_x = 0
    position_y = 0
    deltaX = 0  // Speed vectors for current frame
    deltaY = 0
    xVelocity = 0  // Speed vectors for current movement
    yVelocity = 0
    health = 5
    score = 0
  }
}

