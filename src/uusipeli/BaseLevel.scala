package uusipeli

import scala.collection.mutable.ArrayBuffer
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.File
import scala.util.Random

import uusipeli.model._
import uusipeli.items._



class BaseLevel {
  
  val width = WINDOW_WIDTH
  val height = 1200
  var length = 100
  
  /* Player's starting position. */
  var player_position_x = width / 2
  var player_position_y = 100
  
  var slices = ArrayBuffer[Slice]()
  var items = ArrayBuffer[Item]()
    
  val rand = new scala.util.Random  // Random number generator
  
  var background_music_filename = ""
  var level_title = ""
  var bg_files = List("", "", "", "")
  
    
  def randomItem(): Option[Item] = {
    val itemType = rand.nextInt(7)
    if (itemType == 0) return Some(new Olutpullo())
    if (itemType == 1) return Some(new Noppa())
    if (itemType == 2) return Some(new Nakki())
    if (itemType == 3) return Some(new Jaa())
    if (itemType == 4) return Some(new Kivi())
    if (itemType == 5) return Some(new Prujut())
    if (itemType == 6) return Some(new Spagetti())
    None
  }
  
  
  def reset() = {
    /* Generate map slices */
    slices.clear()
    
    /* First add empty slices. */
    slices += new Slice(bg_files(0))
    slices += new Slice(bg_files(1))
    slices += new Slice(bg_files(2))
  
    slices(0).index = 0
    slices(1).index = 1
    slices(2).index = 2
    
    /* Add level name item to the third slice. */
    slices(2).items += new LevelNameItem(level_title)
    slices(2).items(0).position_x = WINDOW_WIDTH / 2
    
    for (i <- 3 to length) {
      slices += new Slice(bg_files(rand.nextInt(bg_files.length)))
      slices(i).index = i
      slices(i).populate(randomItem)
      items = items ++ slices(i).items
    }
  }
}