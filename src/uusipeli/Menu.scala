package uusipeli

import scala.swing._
import java.awt.Color
import uusipeli.model._
import scala.collection.mutable.Buffer





//tarkista importtien tarpeellisuus
import swing._
import event._
import Swing._
import ListView._

object Menu extends SimpleSwingApplication {
  def top = new MainFrame{
    title = "Otaniemipeli"
    size = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
    background = Color.black
    
    menuBar = new MenuBar {
      contents += new Menu("Choose your level"){
        contents += new MenuItem(Action("JMT"){
          
        })
        contents += new MenuItem(Action("Otakaari"){
          
        })
        contents += new MenuItem(Action("SMT"){
          
        })
        contents += new MenuItem(Action("Exit game"){
          //game.dispose()
          quit()
        })
      }
    }
    

    
    contents = new FlowPanel() {
      preferredSize = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
      val s = new Dimension(300, 50)
      contents += new Button("asd") {
        preferredSize = s
      }
      contents += new Button("dsa") {
        preferredSize = s
      }
      contents += new Button("sad") {
        preferredSize = s
      }
    }
    
    
  }
}
