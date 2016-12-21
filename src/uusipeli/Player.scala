package uusipeli

import java.awt.image.BufferedImage
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File


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
  
  /* Player walking animation */
  var avatar_filename_right = "gfx/128 pixel teekkari oikea.png"
  var avatar_filename_left = "gfx/128 pixel teekkari vasen.png"
  var images = Array.ofDim[BufferedImage](2)
  
  var currImage = 0
  val frameDuration = 400 // How many milliseconds to show one frame of the animation?
  var lastRenderTime: Long = 0
  
  loadResources()

  def loadResources() = {
    /* Load the avatar images. */
    try {
      images(0) = ImageIO.read(new File(avatar_filename_left))
      images(1) = ImageIO.read(new File(avatar_filename_right))
      width = images(0).getWidth()
      height = images(0).getHeight()
    } catch {
      case e: Exception => println("Error: Could not read avatar image file.")
      images(0) = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)
      images(0) = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)
    }
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
    val currTime = scala.compat.Platform.currentTime
    
    if (currTime > (lastRenderTime + frameDuration)) {
      if (currImage == 0) currImage = 1 else currImage = 0
      lastRenderTime = scala.compat.Platform.currentTime
    }
    return images(currImage)
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
  
  /* Not in use any more. */
  def drawImage(): BufferedImage = {
    val avatar = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val g = avatar.createGraphics()
    g.setColor(Color.red)
    g.fillOval(0, 0, 40, 40)
    
    return avatar
  }
}

