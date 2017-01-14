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
  val mygame = Game
  
  def top = new MainFrame {
    title = "Otaniemipeli"
    size = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
    background = Color.black
    
    menuBar = new MenuBar {
      contents += new Menu("Choose your level"){
        contents += new MenuItem(Action("JMT"){
          start_game()
        })
        contents += new MenuItem(Action("Otakaari"){
          go_to_menu()
        })
        contents += new MenuItem(Action("SMT"){
          
        })
        contents += new MenuItem(Action("Exit game"){
          //game.dispose()
          quit()
        })
      }
    }
    

    def go_to_menu() {
      mygame.stopGame
      this.contents = new FlowPanel() {
        preferredSize = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
        val s = new Dimension(300, 50)
        contents += new Button(Action("JMT"){
          start_game()
        }) {
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
    
    def start_game() {
      this.contents = mygame.viewport
      mygame.viewport.requestFocus()
      mygame.startGame()
    }
    
    go_to_menu()
  }
}
