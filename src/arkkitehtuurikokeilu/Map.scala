package arkkitehtuurikokeilu

import scala.collection.mutable.ArrayBuffer

class Map {
  var width = 0
  var height = 0
  
  var player_position_x = 0
  var player_position_y = 0
  
  var background_image_filename = ""
  var background_music_filename = ""
  
  val items = ArrayBuffer[Item]()
}