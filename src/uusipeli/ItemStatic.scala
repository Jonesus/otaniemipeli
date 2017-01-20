package uusipeli

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File
import javax.sound.sampled._

/* This is a companion object for every item type.
 * 
 * It caches the item's image and sound,
 * so that they are not loaded for every instance of this item.
 */
class ItemStatic {
  var imageFilename = ""

  var soundFilename = ""

  var image: Option[BufferedImage] = None

  var wavClip: Option[Clip] = None

  def loadSound(): Unit = {
    if (wavClip.isDefined) return

    try {
      wavClip = Some(AudioSystem.getClip(null))
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
  
  def loadImage() = {
    /* Load the item image. */
    try {
      this.image = Some(ImageIO.read(new File(imageFilename)))
    } catch {
      case e: Exception =>
        println("Error: Could not read item image file.")
        this.image = Some(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB))
    }    
  }
  
  def getImage(): BufferedImage = {
    if (this.image.isDefined) {
      return this.image.get
    } else {
      this.loadImage()
    }
    this.image.get
  }
}
