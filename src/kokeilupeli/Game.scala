package otaniemipeli

import scala.swing._
import java.awt.Color

object Game extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "Otaniemipeli"
    size = new Dimension(400, 400)
    val canvas = new Canvas
    canvas.preferredSize = new Dimension(400, 400)
    contents = canvas
  }
  
  class Canvas extends Panel {
    override def paintComponent(g: Graphics2D) {
      g.setColor(Color.blue)
      g.fillOval(0, 0, 100, 100)
    }
  }
}
