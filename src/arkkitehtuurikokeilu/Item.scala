package arkkitehtuurikokeilu

import java.awt.image.BufferedImage

class Item {
  var position_x: Int = 0
  var position_y: Int = 0
  
  var width: Int = 0
  var height: Int = 0
  
  def render: BufferedImage = {
    new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
  }
  
  def update = {
    
  }
}
