package uusipeli

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Color

class Olutpullo extends Item {
  
  width = 20
  height = 20
  
  val image = drawImage()
  
  override def render: BufferedImage = {
    return image
  }
  
  def drawImage(): BufferedImage = {
    val bottle = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val g = bottle.createGraphics()
    g.setColor(Color.blue)
    g.fillOval(0, 0, 20, 20)
    
    return bottle
  }
}
