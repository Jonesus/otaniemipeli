package uusipeli

import scala.collection.mutable.ArrayBuffer
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.File

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
    background_music_filename = background_music_filename
  }
  
  def loadResources() = {
    /* Load the background image. */
    try {
      background_image = Some(ImageIO.read(new File(background_image_filename)))
    } catch {
      case e: Exception => println("Error: Could not read background image file.")
    }
  }
  
  def update() = {
    for (item <- items) {
      item.update
    }
  }
  
  def getPlayer = {
    this.player
  }
}

