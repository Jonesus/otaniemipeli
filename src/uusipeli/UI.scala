package uusipeli

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Graphics
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File


class UI {
  
  var noppa32: BufferedImage = ImageIO.read(new File("gfx/ 32 noppa.png"))
 
  def drawHealthbar() : BufferedImage = {
    if (Game.player.score == 0) {
      var healthbar: BufferedImage = ImageIO.read(new File("gfx/0health.png"))
      return healthbar
    }
    if (Game.player.score == 1) {
      var healthbar: BufferedImage = ImageIO.read(new File("gfx/1health.png"))
      return healthbar
    }
    if (Game.player.score == 2) {
      var healthbar: BufferedImage = ImageIO.read(new File("gfx/2health.png"))
      return healthbar
    }
    else{
      var healthbar: BufferedImage = ImageIO.read(new File("gfx/3health.png"))
      return healthbar
    }
  }
}