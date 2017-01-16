package uusipeli

import scala.collection.mutable.ArrayBuffer
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Graphics
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File
import java.awt.Font
import java.awt.GraphicsEnvironment


/* This class draws the info bar that show's players health and points. */
class InfoBar {
  
  /* Dice image */
  val dice: BufferedImage = ImageIO.read(new File("gfx/32 noppa.png"))
  
  /* Heart images */
  val healthBarImages = ArrayBuffer[BufferedImage]()
  healthBarImages += ImageIO.read(new File("gfx/0health.png"))
  healthBarImages += ImageIO.read(new File("gfx/1health.png"))
  healthBarImages += ImageIO.read(new File("gfx/2health.png"))
  healthBarImages += ImageIO.read(new File("gfx/3health.png"))
  
  val pointsImage: BufferedImage = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB)
  val pointsGraphics = pointsImage.createGraphics()
  
  /* Font for points text */
  val fontFile = new File("gfx/Gamer.ttf")
  val font = Font.createFont(Font.TRUETYPE_FONT, fontFile)

  /* Add font to local GE, necessary for iOS and Unix. */
  val ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
  ge.registerFont(font);
  
  /* Font object to be used in drawing. */
  val gamerFont = new Font("Gamer", Font.PLAIN, 64)

  /* This method renders the hearts */
  def drawHealthbar(): BufferedImage = {
    var health = Game.player.health
    
    if (health > 3) health = 3
    if (health < 0) health = 0
    
    return healthBarImages(health)
  }

  def drawPoints(g: Graphics2D) = {
    g.setColor(new Color(255, 255, 255)) // a darker green
    g.setFont(gamerFont)
    g.drawString(Game.player.score.toString, 37, 35)
  }
}
