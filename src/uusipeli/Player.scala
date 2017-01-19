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
  
  var position_x = 0.0
  var position_y = 0.0
  var deltaX = 0  // Speed vectors for current frame
  var deltaY = 0
  var xVelocity = 0  // Speed vectors for current movement
  var yVelocity = 0
  var health = 3
  var score = 0
  var stopped = false
  
  val maxSpeedX = 10
  val maxSpeedY = 15
  var acceleration = 1
  
  var Y_RESTING_SPEED = PLAYER_SPEED_DOWN  // Constant speed down
  var level_speed_bonus = 0
  
  var width = 128
  var height = 128
  
  /* Default animations. These can be overriden from the level classes. */
  var soberPlayerAnimation = new Animation
  var sober_player_image_right_filename = "gfx/128 pixel teekkari oikea.png"
  var sober_player_image_left_filename = "gfx/128 pixel teekkari vasen.png"
  
  var drunkenPlayerAnimation = new Animation
  var drunken_player_image_right_filename = "gfx/128 teekkari kanni oikea.png"
  var drunken_player_image_left_filename = "gfx/128 teekkari kanni vasen.png"
  
  var deadPlayerAnimation = new Animation
  var dead_player_image_right_filename = "gfx/128 teekkari dead oikea.png"
  var dead_player_image_left_filename = "gfx/128 teekkari dead vasen.png" 
  
  /* Current animation */
  var playerAnimation = soberPlayerAnimation
  
  def reset() = {
    this.position_x = 0.0
    this.position_y = 0.0
    
    this.deltaX = 0
    this.deltaY = 0
    this.xVelocity = 0
    this.yVelocity = 0
    this.health = 3
    this.score = 0
    this.stopped = false
    this.acceleration = 1
    var Y_RESTING_SPEED = PLAYER_SPEED_DOWN
    this.level_speed_bonus = 0
    
    this.loadResources()
  }

  def loadResources() = {
    /* Prepare the animations. */
    this.soberPlayerAnimation = new Animation
    this.soberPlayerAnimation.frameDuration = 500
    this.soberPlayerAnimation.addFrame(sober_player_image_left_filename)
    this.soberPlayerAnimation.addFrame(sober_player_image_right_filename)
    
    this.drunkenPlayerAnimation = new Animation
    this.drunkenPlayerAnimation.frameDuration = 500
    this.drunkenPlayerAnimation.addFrame(drunken_player_image_right_filename)
    this.drunkenPlayerAnimation.addFrame(drunken_player_image_left_filename)
    
    this.deadPlayerAnimation = new Animation
    this.deadPlayerAnimation.frameDuration = 999999
    this.deadPlayerAnimation.addFrame(dead_player_image_right_filename)
    this.deadPlayerAnimation.addFrame(dead_player_image_left_filename)
    
    this.playerAnimation = this.soberPlayerAnimation
  }
  
  def playerIsDrunk() = {
    this.playerAnimation = drunkenPlayerAnimation
  }
  
  def playerIsSober() = {
    this.playerAnimation = soberPlayerAnimation
  }
  
  def playerIsDead() = {
    this.playerAnimation = deadPlayerAnimation
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
    if (stopped == false) updatePosition()
    if (health > 3) health = 3
  }
  
  def updatePosition() = {
    /* Checks if the player is accelerating or decelerating
     * and changes current frame delta vectors accordingly
     */
    if (deltaX == 0 && xVelocity != 0) {
      deltaX = if (xVelocity > 0) 0 - acceleration else acceleration
    }
    if (yVelocity != Y_RESTING_SPEED + level_speed_bonus) {
      deltaY = if (yVelocity > Y_RESTING_SPEED + level_speed_bonus) 0 - acceleration else acceleration
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
}
