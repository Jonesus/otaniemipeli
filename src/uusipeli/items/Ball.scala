package uusipeli.items

import java.awt.image.BufferedImage
import java.awt.Color
import uusipeli.Item

class Ball extends Item {
  
  width = 20
  height = 20
  
  image = drawImage()
  
  override def render: BufferedImage = {
    return image
  }
  
  def drawImage(): BufferedImage = {
    val ball = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val g = ball.createGraphics()
    g.setColor(Color.blue)
    g.fillOval(0, 0, 20, 20)
    return ball
  }
}
