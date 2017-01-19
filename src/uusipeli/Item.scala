package uusipeli

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File

/* This class represents game items, such as beer bottle and ice cube. */
class Item {
  /* Every item belongs to a slice.
   * These coordinates are in slices coordinate space:
   * (0, 0) is the upper left corner of the slice.
   */
  var positionX: Int = 0
  var positionY: Int = 0
  
  /* Is this item active? Should collisions be processed? */
  var active = true
  
  /* Is this item visible on the map? */
  var visible = true
  
  /* Has this item been disables temporarily? */
  var disabledTemporarily = false
  
  /* When was this item disabled temporarily? */
  var lastDisableTime: Long = 0
  
  /* How long will this item be disabled? */
  var disabledTimeout: Int = 0
  
  /* Width and height of the image. */
  var width: Int = 0
  var height: Int = 0
  
  /* The image of this item. */
  var image: BufferedImage = _
  
  def setImage(i: BufferedImage) = {
    this.image = i
    this.width = this.image.getWidth()
    this.height = this.image.getHeight()
  }
  
  def render: BufferedImage = {
    return this.image
  }
  
  /* Default collision behavior:
   * - We deactivate the item, so it disappears from the screen.
   */
  def processCollision() = {
    this.active = false
  }
  
  def update = {
    
  }
  
  /* This methods disables the item temporarily.
   * 
   * timeout: How long the item should be disabled, in milliseconds
   * */
  def disableTemporarily(timeout: Int) = {
    this.disabledTemporarily = true
    this.disabledTimeout = timeout
    this.lastDisableTime = scala.compat.Platform.currentTime 
  }
  
  /* This method checks if the item should be re-enabled (if it was disabled temporarily).
   * Returns:
   *  false, if the item is (and remains) disabled
   *  true, if the item is (no longer) disabled 
   */
  def checkIfDisabledAndEnable(): Boolean = {
    if (this.disabledTemporarily == true) {
      
      // Has the timeout passed?
      var currTime = scala.compat.Platform.currentTime
      
      if (currTime > (this.lastDisableTime + this.disabledTimeout)) {
        this.disabledTemporarily = false
        return true // Item re-enabled
      } else {
        return false // Item still disabled
      }
    } else {
      return true // Item wasn't disabled in the first place
    }
  }
}
