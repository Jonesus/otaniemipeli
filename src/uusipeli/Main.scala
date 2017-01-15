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
    
    
    def go_to_menu() {
      mygame.stopGame
      this.contents = mymenu
    }
    
    
    def start_game(lvl: BaseLevel) {
      this.contents = mygame.viewport
      mygame.viewport.requestFocus()
      mygame.startGame(lvl)
    }
    
    def startJMT(x: Unit) { start_game(new LevelOne) }
    def startSMT(x: Unit) { start_game(new LevelTwo) }
    def startOTA(x: Unit) { start_game(new LevelThree) }
    
    mymenu.JMTbutton.action = startJMT 
    mymenu.SMTbutton.action = startSMT
    mymenu.OTAbutton.action = startOTA


    go_to_menu()
  }
}
