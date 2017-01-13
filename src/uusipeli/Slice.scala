package uusipeli

import scala.collection.mutable.ArrayBuffer
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.File
import scala.util.Random
import uusipeli.items._
import uusipeli.model._

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
  
  def populate = {
    for (i <- 0 to rand.nextInt(1)) {
      val n: Option[Item] = randomItem()
      n.foreach { i =>
        i.position_x = 160 + rand.nextInt(704)
        i.position_y = 32 + rand.nextInt(64)
        i.slice_index = index
        this.items += i 
      }
    }
  }
  
  def randomItem(): Option[Item] = {
    val itemType = rand.nextInt(7)
    if (itemType == 0) return Some(new Olutpullo())
    if (itemType == 1) return Some(new Noppa())
    if (itemType == 2) return Some(new Nakki())
    if (itemType == 3) return Some(new Jaa())
    if (itemType == 4) return Some(new Kivi())
    if (itemType == 5) return Some(new Prujut())
    if (itemType == 6) return Some(new Spagetti())
    None
  }
}
