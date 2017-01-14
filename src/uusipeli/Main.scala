package uusipeli

import uusipeli.menu._
import scala.swing._
import java.awt.Color
import uusipeli.model._
import scala.collection.mutable.Buffer
import uusipeli.levels._


//tarkista importtien tarpeellisuus
import swing._
import event._
import Swing._
import ListView._



object Main extends SimpleSwingApplication {
  val mygame = Game
  val mymenu = new MenuView
  
  def top = new MainFrame {
    title = "Otaniemipeli"
    size = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
    background = Color.black
    
    menuBar = new MenuBar {
      contents += new Menu("Menu"){
        contents += new MenuItem(Action("Go to menu"){
          go_to_menu()
        })
        contents += new MenuItem(Action("Exit game"){
          quit()
        })
      }
    }
    
    
    def go_to_menu2() {
      mygame.stopGame
      this.contents = mymenu
    }
    
    def go_to_menu() {
      mygame.stopGame
      this.contents = new FlowPanel() {
        preferredSize = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
        val s = new Dimension(300, 50)
        contents += new Button(Action("JMT"){
          start_game(new LevelOne)
        }) {
          preferredSize = s
        }
        contents += new Button(Action("SMT"){
          start_game(new LevelTwo)
        }) {
          preferredSize = s
        }
        contents += new Button("existence is pain") {
          preferredSize = s
        }
      }
    }
    
    def start_game(lvl: Map) {
      this.contents = mygame.viewport
      mygame.viewport.requestFocus()
      mygame.startGame(lvl)
    }
    
    go_to_menu2()
  }
}
