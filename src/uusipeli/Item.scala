package uusipeli

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File

class Item {
  var position_x: Int = 0
  var position_y: Int = 0
  var active = true
  
  var slice_index = 0
  
  var width: Int = 0
  var height: Int = 0
  
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
}
