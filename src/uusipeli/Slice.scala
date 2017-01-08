package uusipeli

import scala.collection.mutable.ArrayBuffer
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.File
import scala.util.Random
import uusipeli.items._

class Slice {
  val rand = new scala.util.Random

  var index = 0
  val width = 600
  val height = 200
  val items = ArrayBuffer[Item]()
  
  val filename = "gfx/slice.png"
  val background_image = Some(ImageIO.read(new File(filename)))
  
  def render: BufferedImage = {
    new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
  }
  
  def populate = {
    for (i <- 0 to rand.nextInt(1)) {
      val n: Option[Item] = randomItem()
      n.foreach { i =>
        i.position_x = 130 + rand.nextInt(340)
        i.position_y = 20 + rand.nextInt(100)
        i.slice_index = index
        this.items += i 
      }
    }
  }
  
  def randomItem(): Option[Item] = {
    val itemType = rand.nextInt(4)
    if (itemType == 0) return Some(new Olutpullo())
    if (itemType == 1) return Some(new Noppa())
    if (itemType == 2) return Some(new Nakki())
    if (itemType == 3) return Some(new Jaa())
    None
  }
}
