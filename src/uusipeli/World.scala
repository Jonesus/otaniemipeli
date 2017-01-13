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
  
  var background_image_filename = ""
  var background_music_filename = ""
  
  /* Items */
  var items = ArrayBuffer[Item]()
  var slices = ArrayBuffer[Slice]()
  
  /* Background image */
  var background_image: Option[BufferedImage] = None
 
  def loadMap(m: Map) = {
    /* We populate this world with the provided map. */
    items = m.items
    slices = m.slices
    
    /* Player's starting position. */
    player.position_x = m.player_position_x
    player.position_y = m.player_position_y
    
    /* Background image filename. */
    background_image_filename = m.background_image_filename
    
    /* Background music filename. */
    background_music_filename = m.background_music_filename
  }
  
  def loadResources() = {
    /* Load the background image. */
    try {
      background_image = Some(ImageIO.read(new File(background_image_filename)))
    } catch {
      case e: Exception => println("Error: Could not read background image file.")
    }
    
    loadMusic()
  }
  
  def update() = {
    for (item <- items) {
      item.update
    }
  }
  
  def getPlayer = {
    this.player
  }

  var backgroundMusicClip: Option[Clip] = None
  
  var backgroundMusicPosition: Long = 0
  
  /* We load the audio file to memory. */
  def loadMusic(): Unit = {
    if (backgroundMusicClip.isDefined) return
    
    try {
      backgroundMusicClip = Some(AudioSystem.getClip())
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
    items.clear()
    slices.clear()
  }
}
