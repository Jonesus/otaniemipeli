package kokeilupeli

import scala.swing._
import java.awt.Color
import java.awt.image.BufferedImage
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer

object Game extends SimpleSwingApplication {
  def top = new MainFrame {
    
    title = "Otaniemipeli"
    size = new Dimension(400, 400)
    background = Color.black;
    val canvas = new Canvas
    canvas.preferredSize = new Dimension(400, 400)
    contents = canvas
  }
  
  class Canvas extends Panel {
    var pallo_x = 10
    var pallo_y = 10
    this.background = Color.black;
    
    val image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB)
    
    for (i <- 0 until 100) {
      image.setRGB(50 + i, 50 + i, Color.blue.getRGB)
    }
    
    val liikutaPalloaTimer = new Timer(40, new ActionListener() {
      override def actionPerformed(e: ActionEvent) {
        liikutaPalloa();
        repaint();
      }
    });
    
    liikutaPalloaTimer.start()
  
    def liikutaPalloa() {
      if (pallo_x > 450) pallo_x = 10 else pallo_x += 1
      if (pallo_y > 450) pallo_y = 10 else pallo_y += 1
      repaint()
    }    
    
    override def paintComponent(g: Graphics2D) {
      g.drawImage(image, null, 0, 0)
      g.setColor(Color.red)
      g.fillOval(pallo_x, pallo_y, 50, 50)
    }
  }
}
