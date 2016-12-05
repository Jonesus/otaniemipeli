package otaniemipeli

import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage


class Item(var x: Int, var y: Int, var func: Unit => Unit, var imgpath: String) {
  var position_x = x
  var position_y = y
  val effect : Unit => Unit = func
  val image : BufferedImage = ImageIO.read(new File(imgpath))
  
  def render() : BufferedImage = {
    return image
  }
}