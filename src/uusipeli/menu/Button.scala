package uusipeli.menu

import scala.swing._
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.File
import uusipeli.model._






class visualButton(x_coord: Int, y_coord: Int, filename: String, func: Unit => Unit) {
  
  // Resize image to half of original
  var image = Some(ImageIO.read(new File(filename))).get
  val resizedImage = new BufferedImage(image.getWidth()/2, image.getHeight()/2, BufferedImage.TYPE_INT_ARGB); 
  val g = resizedImage.createGraphics();
  g.drawImage(image, 0, 0, image.getWidth()/2, image.getHeight()/2, null);
  g.dispose();
  image = resizedImage
  
  
  val width = image.getWidth() 
  val height = image.getHeight()
  val x = WINDOW_WIDTH/2 - width/2  // Center the image
  val y = y_coord
  
  
  var action = func  // What to do when clicked
  
  
  def render() : BufferedImage = {
    return this.image
  }
  
  def isClicked(point: Point) {
    val rect = new Rectangle(x, y, width, height)
    if (rect.contains(point)) {
      action()
    }
  }
  
  
}