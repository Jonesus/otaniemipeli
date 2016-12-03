package otaniemipeli

import scala.swing._
import java.awt.Color
import java.awt.image.BufferedImage
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer

/*
 * This object is the main application.
 */
object Game extends SimpleSwingApplication {
  
  def top = new MainFrame {
    
    title = "Otaniemipeli"
    size = new Dimension(400, 400)
    background = Color.black;    
  }
}
