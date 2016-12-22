package uusipeli

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File

class ItemStatic {
  var imageFilename = ""
  
  var soundFilename = ""
  
  var image: Option[BufferedImage] = None
  
  def getImage(): BufferedImage = {
    if (this.image.isDefined) {
    
      return this.image.get
    
    } else {
      
      /* Load the item image. */
      try {
        this.image = Some(ImageIO.read(new File(imageFilename)))
      } catch {
        case e: Exception => println("Error: Could not read item image file.")
        this.image = Some(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB))
      }
    
    }
 
    this.image.get
  }  
}
