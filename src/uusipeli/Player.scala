package uusipeli

import java.awt.image.BufferedImage
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File
import scala.collection.mutable.ArrayBuffer
import uusipeli.model._
import uusipeli.events.EndGameEvent

/* This class represents the player. */
class Player {
  
  /* Player status: Position, speed, etc. */
  var positionX = 0.0
  var positionY = 0.0
  var deltaX = 0  // Speed vectors for current frame
  var deltaY = 0
  var xVelocity = 0  // Speed vectors for current movement
  var yVelocity = 0
  var health = 3
  var score = 0
  var stopped = false
  var dead = false
  
  val maxSpeedX = 10
  val maxSpeedY = 15
  var acceleration = 1
  
  var Y_RESTING_SPEED = PLAYER_SPEED_DOWN  // Constant speed down
  var levelSpeedBonus = 0
  
  /* Width and height of the image. */
  var width = 128
  var height = 128
  
  /* Default animations. These can be overrun from the level classes. */
  var soberPlayerAnimation = new Animation
  var soberPlayerImageRightFilename = "gfx/128 pixel teekkari oikea.png"
  var soberPlayerImageLeftFilename = "gfx/128 pixel teekkari vasen.png"
  
  var drunkenPlayerAnimation = new Animation
  var drunkenPlayerImageRightFilename = "gfx/128 teekkari kanni oikea.png"
  var drunkenPlayerImageLeftFilename = "gfx/128 teekkari kanni vasen.png"
  
  var deadPlayerAnimation = new Animation
  var deadPlayerImageRightFilename = "gfx/128 teekkari dead oikea.png"
  var deadPlayerImageLeftFilename = "gfx/128 teekkari dead vasen.png" 
  
  /* Win animation: Different number of frames in different levels. See level classes. */
  var winAnimation: Animation = _
  
  
  /* Current animation */
  var playerAnimation = soberPlayerAnimation
  
  /* Reset the player's state. */
  def reset() = {
    this.positionX = 0.0
    this.positionY = 0.0
    
    this.deltaX = 0
    this.deltaY = 0
    this.xVelocity = 0
    this.yVelocity = 0
    this.health = 3
    this.score = 0
    this.stopped = false
    this.dead = false
    this.acceleration = 1
    this.Y_RESTING_SPEED = PLAYER_SPEED_DOWN
    this.levelSpeedBonus = 0
    
    this.loadResources()
  }

  def loadResources() = {
    /* Prepare the animations. */
    this.soberPlayerAnimation = new Animation
    this.soberPlayerAnimation.frameDuration = 500
    this.soberPlayerAnimation.addFrame(soberPlayerImageLeftFilename)
    this.soberPlayerAnimation.addFrame(soberPlayerImageRightFilename)
    
    this.drunkenPlayerAnimation = new Animation
    this.drunkenPlayerAnimation.frameDuration = 500
    this.drunkenPlayerAnimation.addFrame(drunkenPlayerImageRightFilename)
    this.drunkenPlayerAnimation.addFrame(drunkenPlayerImageLeftFilename)
    
    this.deadPlayerAnimation = new Animation
    this.deadPlayerAnimation.frameDuration = 999999
    this.deadPlayerAnimation.addFrame(deadPlayerImageRightFilename)
    this.deadPlayerAnimation.addFrame(deadPlayerImageLeftFilename)
    
    this.playerAnimation = this.soberPlayerAnimation
  }
  
  def playerIsDrunk() = {
    this.playerAnimation = drunkenPlayerAnimation
  }
  
  def playerIsSober() = {
    this.playerAnimation = soberPlayerAnimation
  }
  
  def playerIsDead() = {
    this.dead = true
    this.playerAnimation = deadPlayerAnimation
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
    positionX += dx
    positionY += dy
  }
  
  /* Updates the player's position. This is called in the game loop. */
  def update() = {
    if (stopped == false) updatePosition()
    else if (dead == false) goToGoal()
    if (health > 3) health = 3
  }
  
  /* Move to the goal. Used when the level is completed successfully. */
  def goToGoal() = {
    var dx = 0
    var dy = 0
    if (Game.player.positionX.toInt != WINDOW_WIDTH / 2) {
      dx = if (Game.player.positionX.toInt < WINDOW_WIDTH / 2) 1 else -1
    }
    if (Game.player.positionY.toInt != SLICE_HEIGHT * (Game.world.slices.length - 4)) {
      dy = if (Game.player.positionY.toInt < SLICE_HEIGHT * (Game.world.slices.length - 4)) 1 else -1
    }
    Game.player.move(dx, dy)
  }
  
  def updatePosition() = {
    /* Checks if the player is accelerating or decelerating
     * and changes current frame delta vectors accordingly
     */
    if (deltaX == 0 && xVelocity != 0) {
      deltaX = if (xVelocity > 0) 0 - acceleration else acceleration
    }
    if (yVelocity != Y_RESTING_SPEED + levelSpeedBonus) {
      deltaY = if (yVelocity > Y_RESTING_SPEED + levelSpeedBonus) 0 - acceleration else acceleration
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
    
    /* Update the position according to the speed. */
    move(xVelocity, yVelocity)
    deltaX = 0
    deltaY = 0
    
    if ((positionX - (width / 2)) < WALL_WIDTH) {
      xVelocity = 0
      positionX = WALL_WIDTH + (width / 2)
    }
    if ((positionX + (width / 2)) > (SLICE_WIDTH - WALL_WIDTH)) {
      xVelocity = 0
      positionX = SLICE_WIDTH - WALL_WIDTH - (width / 2)
    }
  }
}
