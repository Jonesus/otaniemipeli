package uusipeli

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Graphics
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File
import java.awt.Font
import java.awt.GraphicsEnvironment

class UI {
  
  var noppa32: BufferedImage = ImageIO.read(new File("gfx/32 noppa.png"))
  var image : BufferedImage = new BufferedImage(37*2-5,32,BufferedImage.TYPE_INT_RGB)
  var fontgraphic: Graphics2D = image.createGraphics()
  val fontFile = new File("gfx/Gamer.ttf")
  val gamerfont = Font.createFont(Font.TRUETYPE_FONT, fontFile)
  
  
  def drawHealthbar() : BufferedImage = {
    if (Game.player.health == 0) {
      var healthbar: BufferedImage = ImageIO.read(new File("gfx/0health.png"))
      return healthbar
    }
    if (Game.player.health == 1) {
      var healthbar: BufferedImage = ImageIO.read(new File("gfx/1health.png"))
      return healthbar
    }
    if (Game.player.health == 2) {
      var healthbar: BufferedImage = ImageIO.read(new File("gfx/2health.png"))
      return healthbar
    }
    else{
      var healthbar: BufferedImage = ImageIO.read(new File("gfx/3health.png"))
      return healthbar
    }
  }
  
  def pointsfunction(fontgraphic: Graphics2D) {
    fontgraphic.setColor(new Color(255, 255, 255)) // a darker green
    fontgraphic.setFont(new Font("Gamer", Font.PLAIN, 64))
    fontgraphic.drawString(Game.player.score.toString, 37, 35)
    
  }
}