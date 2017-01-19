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

import scala.swing.event._
import java.awt.event._

/* The main menu. */
class MenuView extends Panel {
  
  focusable = true
  listenTo(mouse.clicks)
  listenTo(mouse.moves)
  
  reactions += {
      case e: MouseClicked => {
        for (button <- buttons) {
          button.isClicked(e.point)
        }
      }
      case e: MouseMoved => {
        for (button <- buttons) {
          button.isHovered(e.point)
        }
        this.repaint()
      }
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
  var buttons = new ArrayBuffer[VisualButton]()
  buttons += JMTbutton
  buttons += SMTbutton
  buttons += OTAbutton
  
  
  def renderButtons(g: Graphics2D) {
    var menuGraphics = this.menuImage.getGraphics()
    menuGraphics.setColor(new Color(0, 0, 0))
    menuGraphics.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT)
    
    for (button <- buttons) {
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
    renderButtons(g)
    g.drawImage(menuImage, null, 0, 0)
    renderText(g)
  }
}