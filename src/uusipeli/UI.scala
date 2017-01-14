package uusipeli

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File


class UI {
  
  var health: BufferedImage = ImageIO.read(new File("gfx/32 heart full.png"))
  var nohealth: BufferedImage = ImageIO.read(new File("gfx/32 heart empty.png"))
  var noppa32: BufferedImage = ImageIO.read(new File("gfx/ 32 noppa.png"))
  
  var w: Int = 36*5
  var h: Int = 69
  
 
  
  def drawHealthbar() = {
    var healthbar: Graphics = combined.getGraphics()
    if (Player.points >= 1) {
      healthbar.drawImage(health, 0, 0, null);
    }
   // else{
    //  healthbar.drawImage(nohealth, 0, 0, null);
    //}
    if (Player.points >= 2) {
      healthbar.drawImage(health, 37, 0, null);
    }
    else{
      healthbar.drawImage(nohealth, 37*, 0, null)
    }
    if (Player.points >= 3) {
      healthbar.drawImage(health, 37*2, 0, null)
    }
    else{
      healthbar.drawImage(nohealth, 37*2, 0, null)
    }
    if (Player.points >= 4) {
      healthbar.drawImage(health, 37*3, 0, null)
    }
    else{
      healthbar.drawImage(nohealth, 37*3, 0, null)
    }
    if (Player.points >= 4) {
      healthbar.drawImage(health, 37*4, 0, null)
    }
    else{
      healthbar.drawImage(nohealth, 37*4, 0, null)
    }
    ImageIO.write(healthbar, "PNG", new FILE("gfx/healthbar.png")
  }
}