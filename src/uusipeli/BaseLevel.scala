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
  val rand = new scala.util.Random  // Random number generator
    
  var slices = ArrayBuffer[Slice]()
  
  
  
  val width = WINDOW_WIDTH
  var length = 100
  val height = SLICE_HEIGHT * this.length
  
  var speed_bonus = 0
  
  /* Player's starting position. */
  var player_position_x = width / 2
  var player_position_y = 100
  
  var background_music_filename = ""
  var level_title = ""
  var bg_files = List("", "", "", "")
  
    
  def randomItem(): Option[Item] = {
    val itemType = rand.nextInt(7)
    if (itemType == 0) return Some(new Beer())
    if (itemType == 1) return Some(new Dice())
    if (itemType == 2) return Some(new Sausage())
    if (itemType == 3) return Some(new Ice())
    if (itemType == 4) return Some(new Stone())
    if (itemType == 5) return Some(new Cheatsheet())
    if (itemType == 6) return Some(new Spaghetti())
    None
  }
  
  
  def reset() = {
    Game.player.level_speed_bonus = speed_bonus
    
    /* Generate map slices */
    slices.clear()
    
    /* First add empty slices. */
    slices += new Slice(bg_files(0))
    slices += new Slice(bg_files(1))
    slices += new Slice(bg_files(2))
  
    /* Set slice indexes. */
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
    }
  }
}
