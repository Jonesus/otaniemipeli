package uusipeli

import scala.collection.mutable.ArrayBuffer
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.File
import java.io.File
import javax.sound.sampled._

class World(player: Player) {
  
  /* Width and height of the world. */
  val width = 0
  val height = 0
  
  /* File names */
  var background_music_filename = ""
  var level_name_image_filename = ""
  
  /* Items */
  var slices = ArrayBuffer[Slice]()

  var level_name_image: Option[BufferedImage] = None
 
  def loadLevel(l: BaseLevel) = {
    /* We populate this world with the provided map. */
    slices = l.slices
    
    /* Player's starting position. */
    player.position_x = l.player_position_x
    player.position_y = l.player_position_y

    background_music_filename = l.background_music_filename
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
}
