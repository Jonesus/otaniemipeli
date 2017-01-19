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
  val game = Game
  val menu = new MenuView
  
  def top = new MainFrame {
    title = "Otaniemipeli"
    size = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
    background = Color.black
    
    def goToMenu() {
      println("goToMenu() called. Current thread: " + Thread.currentThread())
      this.contents = menu
    }
    
    def startNewGame(lvl: BaseLevel) {
      this.contents = Main.game.viewport
      game.viewport.requestFocus()
      game.startGame(lvl)
    }
    
    def startJMT(x: Unit) = { startNewGame(new LevelOne) }
    def startSMT(x: Unit) = { startNewGame(new LevelTwo) }
    def startOTA(x: Unit) = { startNewGame(new LevelThree) }
    
    menu.JMTbutton.action = startJMT 
    menu.SMTbutton.action = startSMT
    menu.OTAbutton.action = startOTA
    
    game.setMenuCallback(this.goToMenu)
    goToMenu()
  }
}
