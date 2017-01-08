package uusipeli

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File
import javax.sound.sampled._

class ItemStatic {
  var imageFilename = ""
  
  var soundFilename = ""
  
  var image: Option[BufferedImage] = None
  
  var wavClip: Option[Clip] = None
  
  /* We load the audio file to memory. */
  def loadSound(): Unit = {
    if (wavClip.isDefined) return
    
    try {
      wavClip = Some(AudioSystem.getClip())
      wavClip.get.open(AudioSystem.getAudioInputStream(new File(soundFilename)))
    } catch {
      case e: Exception => println("Could not open sound file " + soundFilename + ": " + e.toString())
    }
  }
  
  def playSound() = {
    try {
      wavClip.foreach { c =>
        c.setMicrosecondPosition(0)
        c.start()
      }
    } catch {
      case e: Exception => println("Error: Could not play sound file " + soundFilename + ": " + e.toString())
    }
  }
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
