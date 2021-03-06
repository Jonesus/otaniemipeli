package uusipeli.menu

import scala.swing._
import java.awt.Color
import uusipeli.model._
import scala.collection.mutable.Buffer
import scala.collection.mutable.ArrayBuffer
import java.awt.image.BufferedImage
import uusipeli.levels._

import java.awt.Font
import java.awt.GraphicsEnvironment
import java.io.File

import java.awt.geom._
import javax.imageio.ImageIO
import scala.swing.event._
import java.awt.event._

/* The main menu. */
class MenuView extends Panel {
  focusable = true
  listenTo(mouse.clicks)
  listenTo(mouse.moves)
  
  reactions += {
      case e: MouseClicked => {
        if (!showHelp) {
          for (button <- menuButtons) {
            button.isClicked(e.point)
          }
        }
        for (button <- helpButtons) {
          button.isClicked(e.point)
        }
      }
      case e: MouseMoved => {
        for (button <- menuButtons) {
          button.isHovered(e.point)
        }
        this.repaint()
      }
    }
  
  
  var showHelp = false
  var instructionsImage = Some(ImageIO.read(new File("gfx/instructions.png"))).get
  def toggleHelp(x: Unit) {
    if (this.showHelp) this.showHelp = false
    else this.showHelp = true
  }
  
  val fontFile = new File("gfx/Gamer.ttf")
  val newFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
  val ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
  ge.registerFont(newFont);
  
  
  this.preferredSize = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
  var menuImage = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB)
  
  
  var JMTbutton = new VisualButton(0, 200, "gfx/title jmt.png", "gfx/title jmt selected.png", null)
  var SMTbutton = new VisualButton(0, 300, "gfx/title smt.png", "gfx/title smt selected.png", null)
  var OTAbutton = new VisualButton(0, 400, "gfx/title otakaari.png", "gfx/title otakaari selected.png", null)
  var helpbutton = new VisualButton(0, 500, "gfx/help.png", "gfx/help.png", toggleHelp)
  helpbutton.x = -57
  helpbutton.y = 639
  var menuButtons = new ArrayBuffer[VisualButton]()
  var helpButtons = new ArrayBuffer[VisualButton]()
  menuButtons += JMTbutton
  menuButtons += SMTbutton
  menuButtons += OTAbutton
  helpButtons += helpbutton
  
  
  def renderButtons(g: Graphics2D) {
    var menuGraphics = this.menuImage.getGraphics()
    menuGraphics.setColor(new Color(0, 0, 0))
    menuGraphics.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT)
    
    for (button <- menuButtons ++ helpButtons) {
      menuGraphics.drawImage(button.render(),
                             button.x,
                             button.y,
                             null)
    }
  }
  
  def renderText(g: Graphics2D) {
    g.setColor(new Color(255, 255, 255))
    g.setFont(new Font("Gamer", Font.PLAIN, 100))
    g.drawString("TEEKKARISIMULAATTORI", 130, 80)
  }

  override def paintComponent(g: Graphics2D) {
    if (!showHelp) {
      renderButtons(g)
      g.drawImage(menuImage, null, 0, 0)
      renderText(g)
    }
    else {
      g.drawImage(instructionsImage, null, 0, 0)
    }
    this.repaint()
  }
}