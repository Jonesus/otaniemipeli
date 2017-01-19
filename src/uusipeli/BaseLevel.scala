package uusipeli

import scala.collection.mutable.ArrayBuffer
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.File
import scala.util.Random

import uusipeli.model._
import uusipeli.items._


/* This class is a generic level class. It must be subclassed.
 * 
 * Levels are loaded in class World.
 */
class BaseLevel {
  val rand = new scala.util.Random  // Random number generator
  
  /* Slices that have a background and hold items. */
  var slices = ArrayBuffer[Slice]()
  
  /* Dimensions of this level. */
  val width = WINDOW_WIDTH
  var length = 100
  val height = SLICE_HEIGHT * this.length
  
  /* Player's starting position. */
  var playerPositionX = width / 2
  var playerPositionY = 100
  
  /* Filenames for background music and images. */
  var backgroundMusicFilename = ""
  var levelTitleFilename = ""
  var bgFiles = List("", "", "", "")
  var levelGoalFilename = "gfx/64 maaliviiva.png"
  var levelGoalItem = ""
  
  /* Player's animation frames. */
  var soberPlayerImageLeftFilename = ""
  var soberPlayerImageRightFilename = ""
    
  var drunkenPlayerImageLeftFilename = ""
  var drunkenPlayerImageRightFilename = ""
    
  var deadPlayerImageFeftFilename = ""
  var deadPlayerImageRightFilename = ""
  
  /* Player's speed bonus. */
  var levelSpeedBonus = 0
  
  /* This method is used to generate random items. */
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
  
  /* This method generates the slices for this level. */
  def generateSlices() = {
    /* Clear the slices. */
    this.slices.clear()
    
    /* First add empty slices. */
    this.slices += new Slice(bgFiles(0))
    this.slices += new Slice(bgFiles(1))
    this.slices += new Slice(bgFiles(2))
  
    /* Set slice indexes. */
    this.slices(0).index = 0
    this.slices(1).index = 1
    this.slices(2).index = 2
    
    /* Add level title item to the third slice. */
    val name = new LevelNameItem(this.levelTitleFilename)
    name.positionX = WINDOW_WIDTH / 2
    this.slices(2).items += name
    
    /* Populate slices with random items. Use a random slice background. */
    for (i <- 3 to this.length - 6) {
      this.slices += new Slice(bgFiles(rand.nextInt(bgFiles.length)))
      this.slices(i).index = i
      this.slices(i).populate(this.randomItem)
    }
  
    /* Finally, add the level goal to the last slice. */
    this.slices += new Slice(bgFiles(0))
    this.slices(this.slices.length - 1).index = this.slices.length - 1
    val lastSlice = this.slices(this.slices.length - 1)
    
    val goal = new LevelGoalItem(this.levelGoalFilename)
    goal.positionX = WINDOW_WIDTH / 2
    lastSlice.items += goal
    
    /* Add a small visual buffer to the end of the level. */
    for (i <- this.length - 4 to this.length) {
      this.slices += new Slice(bgFiles(rand.nextInt(bgFiles.length)))
      this.slices(i).index = i
    }
    
    val goalSlice = this.slices(this.length - 3)
    goalSlice.items += new LevelGoalItem(this.levelGoalItem)
  }
}
