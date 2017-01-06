package uusipeli

import scala.collection.mutable.ArrayBuffer
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.File
import scala.util.Random



class Map {
  var width = 0
  var height = 0
  
  var player_position_x = 0
  var player_position_y = 0
  
  var background_image_filename = ""
  var background_music_filename = ""
  
  var slices = ArrayBuffer[Slice]()
  var items = ArrayBuffer[Item]()
}
