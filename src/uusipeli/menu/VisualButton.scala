package uusipeli.menu

import scala.swing._
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.File
import uusipeli.model._






class VisualButton(xCoord: Int, yCoord: Int, filename: String, filename2: String, func: Unit => Unit) {
  
  // Resize image to half of original
  def loadImage(path: String): BufferedImage = {
    var image = Some(ImageIO.read(new File(path))).get
    val resizedImage = new BufferedImage(image.getWidth()/2, image.getHeight()/2, BufferedImage.TYPE_INT_ARGB); 
    val g = resizedImage.createGraphics();
    g.drawImage(image, 0, 0, image.getWidth()/2, image.getHeight()/2, null);
    g.dispose();
    return resizedImage
  }
  
  val unselectedImage = loadImage(filename)
  val selectedImage = loadImage(filename2)
  var activeImage = unselectedImage
  
  
  val width = unselectedImage.getWidth() 
  val height = unselectedImage.getHeight()
  var x = WINDOW_WIDTH/2 - width/2  // Center the image
  var y = yCoord
  
  
  var action = func  // What to do when clicked
  
  
  def render() : BufferedImage = {
    return this.activeImage
  }
  
  def isClicked(point: Point) {
    val rect = new Rectangle(x, y, width, height)
    if (rect.contains(point)) {
      action()
    }
  }
  
  def isHovered(point: Point) {
    val rect = new Rectangle(x, y, width, height)
    if (rect.contains(point)) {
      this.activeImage = this.selectedImage
    }
    else {
      this.activeImage = this.unselectedImage
    }
  }
}