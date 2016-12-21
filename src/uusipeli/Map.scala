package uusipeli

import scala.collection.mutable.ArrayBuffer
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.File


class Map {
  var width = 0
  var height = 0
  
  var player_position_x = 0
  var player_position_y = 0
  
  var background_image_filename = ""
  var background_music_filename = ""
  
  var slices = ArrayBuffer[Slice]()
  val items = ArrayBuffer[Item]()
}

class Slice {
  var index = 0
  val width = 600
  val height = 200
  val items = ArrayBuffer[Item]()
  
  val filename = "gfx/slice.png"
  val background_image = Some(ImageIO.read(new File(filename)))
  
  def render: BufferedImage = {
    new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
  }
  
}