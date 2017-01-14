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


class MenuView extends Panel {
  
  focusable = true
  listenTo(mouse.clicks)
  
  reactions += {
      case e: MouseClicked => {
        println("Mouse clicked at " + e.point)
        for (button <- buttons) {
          button.isClicked(e.point)
        }
      }
    }
  
  
  
  val fontFile = new File("gfx/Gamer.ttf")
  val new_font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
  val ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
  ge.registerFont(new_font);
  
  
  this.background = Color.darkGray
  this.preferredSize = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
  val menu_image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB)
  
  
  var JMTbutton = new visualButton(0, 200, "gfx/title jmt.png", null)
  var SMTbutton = new visualButton(0, 300, "gfx/title smt.png", null)
  var OTAbutton = new visualButton(0, 400, "gfx/title otakaari.png", null)
  var buttons = new ArrayBuffer[visualButton]()
  buttons += JMTbutton
  buttons += SMTbutton
  buttons += OTAbutton
  
  
  def render(g: Graphics2D) {
    g.setColor(new Color(255, 255, 255))
    g.setFont(new Font("Gamer", Font.PLAIN, 100))
    g.drawString("TEEKKARISIMULAATTORI", 130, 80)
    
    
    var menu_graphics = menu_image.getGraphics()
    
    for (button <- buttons) {
      menu_graphics.drawImage(button.render(),
                              button.x,
                              button.y,
                              null)
    }
    
  }
  
  
  override def paintComponent(g: Graphics2D) {
    render(g)
    g.drawImage(menu_image, null, 0, 0)
  }
}