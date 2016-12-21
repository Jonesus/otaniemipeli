package uusipeli

import scala.swing._
import java.awt.Color
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer
import java.awt.image.BufferedImage

/*
 * This object is the main application of the game.
 */
object Game extends SimpleSwingApplication {
  
  /* Keys being held down */
  var key_w = false
  var key_s = false
  var key_a = false
  var key_d = false
  
  
  /* Game window width and height */
  val window_width = 1000
  val window_height = 600
  
  /* Game window title */
  val window_title = "Otaniemipelikö?"
  
  /* Frame rate */
  val frame_rate = 60
  
  val player = new Player
  val world = new World(player)
  
  world.loadMap(new LevelOne)
  world.loadResources()
  
  /* Viewport to the world */
  val viewport = new Viewport(world, window_width, window_height, (window_width / 2), (window_height / 2))
  
  def top = new MainFrame {
    ignoreRepaint = true
    title = window_title
    size = new Dimension(window_width, window_height)
    background = Color.black
    viewport.preferredSize = new Dimension(window_width, window_height)
    
    contents = viewport
  }
  
  def update() = {
    processKeys()
    viewport.update()
    player.update()
    world.update()
  }  
  
  // Timer events
  val renderingTimer = new Timer((1000 / frame_rate), new ActionListener() {
    override def actionPerformed(e: ActionEvent) {
      update()
      viewport.repaint()
    }
  })
  
  def processKeys() {
    if (key_w) {
      player.turnUp()
    }
    if (key_s) {
      player.turnDown()
    }
    if (key_a) {
      player.turnLeft()
    }
    if (key_d) {
      player.turnRight()
    }
  }
  
  // Keyboard events
  def keyPressed(k: String) = {
    if (k == "w") {
      key_w = true
    }
    if (k == "s") {
      key_s = true
    }
    if (k == "a") {
      key_a = true
    }
    if (k == "d") {
      key_d = true
    }
  }
  def keyReleased(k: String) = {
    if (k == "w") {
      key_w = false
    }
    if (k == "s") {
      key_s = false
    }
    if (k == "a") {
      key_a = false
    }
    if (k == "d") {
      key_d = false
    }
  }
  
  renderingTimer.start()
}

