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
  var level_title_filename = ""
  var bg_files = List("", "", "", "")
  var level_goal_filename = ""
    
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
    this.slices.clear()
    
    /* First add empty slices. */
    this.slices += new Slice(bg_files(0))
    this.slices += new Slice(bg_files(1))
    this.slices += new Slice(bg_files(2))
  
    /* Set slice indexes. */
    this.slices(0).index = 0
    this.slices(1).index = 1
    this.slices(2).index = 2
    
    /* Add level title item to the third slice. */
    val name = new LevelNameItem(this.level_title_filename)
    name.position_x = WINDOW_WIDTH / 2
    this.slices(2).items += name
    
    for (i <- 3 to this.length) {
      this.slices += new Slice(bg_files(rand.nextInt(bg_files.length)))
      this.slices(i).index = i
      this.slices(i).populate(this.randomItem)
    }
  
    // Finally, add the level goal to the last slice.
    this.slices += new Slice(bg_files(0))
    this.slices(this.slices.length - 1).index = this.slices.length - 1
    val lastSlice = this.slices(this.slices.length - 1)
    
    val goal = new LevelGoalItem(this.level_goal_filename)
    goal.position_x = WINDOW_WIDTH / 2
    lastSlice.items += goal
    
    // Add small visual buffer to the end of the level
    for (i <- this.slices.length to this.slices.length + 3) {
      this.slices += new Slice(bg_files(rand.nextInt(bg_files.length)))
      this.slices(i).index = i
    }
    
  }
}
