package uusipeli.menu

import scala.swing._
import java.awt.Color
import uusipeli.model._
import scala.collection.mutable.Buffer
import java.awt.image.BufferedImage
import uusipeli.levels._

import java.awt.Font
import java.awt.GraphicsEnvironment
import java.io.File

import java.awt.geom._


//tarkista importtien tarpeellisuus
import swing._
import event._
import Swing._
import ListView._


class MenuView extends Panel {
  
  val fontFile = new File("gfx/Gamer.ttf")
  val new_font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
  val ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
  ge.registerFont(new_font);
  
  
  this.background = Color.darkGray
  this.preferredSize = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)

  val menu_image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB)

  var rec = new RoundRectangle2D.Double(100, 100, 400, 200, 50, 50)
  
  
  def render(g: Graphics2D) {
    g.setColor(new Color(255, 255, 255))
    g.setFont(new Font("Gamer", Font.PLAIN, 60))
    g.drawString("vittu JEEE", 300, 300)
    
    g.setColor(Color.CYAN)
    g.fill(rec)

    
  }
  
  
  override def paintComponent(g: Graphics2D) {
    render(g)
    g.drawImage(menu_image, null, 0, 0)
  }
}