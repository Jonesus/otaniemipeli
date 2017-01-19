package uusipeli

import scala.collection.mutable.ArrayBuffer
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.File
import scala.util.Random
import uusipeli.items._
import uusipeli.model._

/* This class represents a slice. The game world is composed of slices that hold items. */
class Slice(filename: String) {
  val rand = new scala.util.Random

  var index = 0
  val width = SLICE_WIDTH
  val height = SLICE_HEIGHT
  
  val items = ArrayBuffer[Item]()
  
  val background_image = Some(ImageIO.read(new File(filename)))
  
  def render: BufferedImage = {
    new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
  }
  
  def populate(itemFunc: () => Option[Item]) = {
    val n: Option[Item] = itemFunc()
    n.foreach { i =>
      i.position_x = 160 + rand.nextInt(704)
      i.position_y = 32 + rand.nextInt(64)
      this.items += i 
    }
  }
}
