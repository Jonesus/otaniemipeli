package uusipeli

import scala.collection.mutable.ArrayBuffer
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.File
import scala.util.Random
import uusipeli.items.Olutpullo

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
    var i = 0
    for (i <- 0 to rand.nextInt(1)) {
      items += new Olutpullo
      items(i).position_x = 130 + rand.nextInt(340)
      items(i).position_y = 20 + rand.nextInt(100)
      items(i).width = 20
      items(i).height = 64
      items(i).slice_index = index
    }
  }
}
