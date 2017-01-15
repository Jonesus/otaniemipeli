package uusipeli
import javax.swing.Timer
import java.awt.image.BufferedImage
import scala.collection.mutable.ArrayBuffer
import java.io.File
import javax.imageio.ImageIO

class Animation {
  var frameDuration = 400
  val frames = ArrayBuffer[BufferedImage]()
  var width = 0
  var height = 0
  var lastRenderTime: Long = 0
  var currFrame = 0
  
  def addFrame(fileName: String) = {
    try {
      val image = ImageIO.read(new File(fileName))
      width = image.getWidth()
      height = image.getHeight()
      this.frames += image
    } catch {
      case e: Exception => println("Could not load animation frame from file " + fileName + ": " + e.toString())
    }
  }
  
  def render(): BufferedImage = {
    if (frames.size == 0) return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)
    
    val currTime = scala.compat.Platform.currentTime
    if (currTime > (this.lastRenderTime + this.frameDuration)) {
      //Changes the frame of the animation if sufficient time has passed.
      if (this.currFrame == (this.frames.size - 1)) this.currFrame = 0 else this.currFrame += 1
      this.lastRenderTime = scala.compat.Platform.currentTime
    }
    return this.frames(this.currFrame)
  }
}
