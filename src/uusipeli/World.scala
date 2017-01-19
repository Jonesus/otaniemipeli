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
  var background_music_filename = ""
  var level_title_filename = ""
  
  /* Items */
  var slices = ArrayBuffer[Slice]()

  var level_name_image: Option[BufferedImage] = None
 
  def loadLevel(l: BaseLevel) = {
    player.reset()
    /* We populate this world with the provided map. */
    l.generateSlices()
    
    slices = l.slices
    
    /* Player's starting position. */
    player.position_x = l.player_position_x
    player.position_y = l.player_position_y

    background_music_filename = l.background_music_filename
    level_title_filename = l.level_title_filename
    
    /* Player's animations. */
    player.sober_player_image_left_filename = l.sober_player_image_left_filename
    player.sober_player_image_right_filename = l.sober_player_image_right_filename
    
    player.drunken_player_image_left_filename = l.drunken_player_image_left_filename
    player.drunken_player_image_right_filename = l.drunken_player_image_right_filename
    
    player.dead_player_image_left_filename = l.dead_player_image_left_filename
    player.dead_player_image_right_filename = l.dead_player_image_right_filename
    
    /* Player's speed bonus. */
    player.level_speed_bonus = l.level_speed_bonus
    
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
      backgroundMusicClip.get.open(AudioSystem.getAudioInputStream(new File(background_music_filename)))
    } catch {
      case e: Exception => println("Could not open sound file " + background_music_filename + ": " + e.toString())
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
    val itemX = item.position_x - (item.width / 2)
    val itemY = slice.index * slice.height + item.position_y - (item.height / 2)
    
    val playerX = (this.player.position_x - (this.player.width / 2)).toInt
    val playerY = (this.player.position_y - (this.player.height / 2)).toInt
    
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
