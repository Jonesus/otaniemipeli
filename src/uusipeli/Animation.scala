package uusipeli
import javax.swing.Timer
import java.awt.image.BufferedImage
import scala.collection.mutable.ArrayBuffer
import java.io.File
import javax.imageio.ImageIO

/* This class represents an animation that is build from BufferedImage frames. */
class Animation {
  
  /* The default duration of a frame, in milliseconds. */
  var frameDuration = 400
  private val frames = ArrayBuffer[BufferedImage]()
  
  private var width = 0
  private var height = 0
  
  /* When was the frame changed? */
  private var lastRenderTime: Long = 0
  private var currFrame = 0
  
  /* Adds a new frame to the list.
   * 
   * fileName: The file name of the frame to be added.
   */
  def addFrame(fileName: String) = {
    try {
      val image = ImageIO.read(new File(fileName))
      this.width = image.getWidth()
      this.height = image.getHeight()
      this.frames += image
    } catch {
      case e: Exception => println("Could not load animation frame from file " + fileName + ": " + e.toString())
    }
  }
  
  /* Renders the current frame. If frameDuration has passed since the last render,
   * changes the image.
   */
  def render(): BufferedImage = {
    /* If the animation is empty, returns a 1 pixel x 1 pixel empty image. */
    if (this.frames.size == 0) return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)
    
    val currTime = scala.compat.Platform.currentTime
    if (currTime > (this.lastRenderTime + this.frameDuration)) {
      /* Changes the frame. */
      if (this.currFrame == (this.frames.size - 1)) this.currFrame = 0 else this.currFrame += 1
      
      this.lastRenderTime = scala.compat.Platform.currentTime
    }
    return this.frames(this.currFrame)
  }
}
