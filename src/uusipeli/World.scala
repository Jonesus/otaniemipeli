package uusipeli

import scala.collection.mutable.ArrayBuffer
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.File
import java.io.File
import javax.sound.sampled._
import uusipeli.events.EndGameEvent

class World(player: Player) {
  
  /* Width and height of the world. */
  val width = 0
  val height = 0
  
  /* File names */
  var backgroundMusicFilename = ""
  
  /* Items */
  var slices = ArrayBuffer[Slice]()

  def loadLevel(l: BaseLevel) = {
    this.backgroundMusicFilename = l.backgroundMusicFilename
    
    /* We populate this world with the provided map. */
    l.generateSlices()
    this.slices = l.slices
    
    /* Reset all players variables and load new images */
    player.reset()    
        
    /* Player's animations. */
    player.soberPlayerImageLeftFilename = l.soberPlayerImageLeftFilename
    player.soberPlayerImageRightFilename = l.soberPlayerImageRightFilename
    
    player.drunkenPlayerImageLeftFilename = l.drunkenPlayerImageLeftFilename
    player.drunkenPlayerImageRightFilename = l.drunkenPlayerImageRightFilename
    
    player.deadPlayerImageLeftFilename = l.deadPlayerImageFeftFilename
    player.deadPlayerImageRightFilename = l.deadPlayerImageRightFilename
    
    /* Player's starting position. */
    player.positionX = l.playerPositionX
    player.positionY = l.playerPositionY
    
    /* Player's speed bonus. */
    player.levelSpeedBonus = l.levelSpeedBonus
    
    /* Load the animations. */
    player.loadResources()
    
    /* We start with a sober player. */
    player.playerIsSober()
  }
  
  def loadResources() = {
    loadMusic()
  }
  
  def update() = {
    for (slice <- this.slices) {
      for (item <- slice.items) {
        item.update
      }
    }
  }
  
  def getPlayer() = {
    this.player
  }

  var backgroundMusicClip: Option[Clip] = None
  
  var backgroundMusicPosition: Long = 0
  
  /* We load the audio file to memory. */
  def loadMusic(): Unit = {
    if (backgroundMusicClip.isDefined) return
    
    try {
      backgroundMusicClip = Some(AudioSystem.getClip(null))
      backgroundMusicClip.get.open(AudioSystem.getAudioInputStream(new File(backgroundMusicFilename)))
    } catch {
      case e: Exception => println("Could not open sound file " + backgroundMusicFilename + ": " + e.toString())
    }
  }
  
  def playMusic() = {
    this.backgroundMusicClip.foreach {x =>
      x.setMicrosecondPosition(0)
      x.start()
    
    }
  }
  
  def stopMusic() = {
    this.backgroundMusicClip.foreach { x => x.stop() }
  }
  
  def pauseMusic() = {
    this.backgroundMusicClip.foreach { x =>
      this.backgroundMusicPosition = x.getMicrosecondPosition()
      x.stop()    
    }
  }
  
  def continueMusic() = {
    this.backgroundMusicClip.foreach {x =>
      x.setMicrosecondPosition(this.backgroundMusicPosition)
      x.start()
    }
  }
  
  def reset() = {
    this.slices.clear()
  }
  
  def playerIntersectsWithItem(slice: Slice, item: Item) : Boolean = {
    /*
     * if (X1+W1<X2 or X2+W2<X1 or Y1+H1<Y2 or Y2+H2<Y1):
     * Intersection = Empty
     * else:
     * Intersection = Not Empty
     */
    val itemX = item.positionX - (item.width / 2)
    val itemY = slice.index * slice.height + item.positionY - (item.height / 2)
    
    val playerX = (this.player.positionX - (this.player.width / 2)).toInt
    val playerY = (this.player.positionY - (this.player.height / 2)).toInt
    
    if ( (itemX + item.width < playerX).||(playerX + this.player.width < itemX).||(itemY + item.height < playerY).||(playerY + this.player.height < itemY)) {
      return false
    }
    
    return true
  }
  
  def checkPlayerCollisions() = {
    for (slice <- this.slices) {
      for (item <- slice.items) {
        if (this.playerIntersectsWithItem(slice, item) && item.active == true) {
          item.processCollision()
        }
      }
    }
  }
  
  def checkPlayerDeath() = {
    if (this.player.health == 0) {
      this.player.playerIsDead()
      Game.addEvent(new EndGameEvent())
    }
  }  
}
