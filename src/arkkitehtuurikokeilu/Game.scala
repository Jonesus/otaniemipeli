package arkkitehtuurikokeilu

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
  
  /* Game window width and height */
  val window_width = 600
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
  val viewport = new Viewport(world, window_width, window_height, 300, 300)
  
  def top = new MainFrame {
    title = window_title
    size = new Dimension(window_width, window_height)
    background = Color.black

    viewport.preferredSize = new Dimension(window_width, window_height)
    contents = viewport
  }
  
  def update() = {
    player.update()
    world.update()
  }  
  
  // Timer events
  val renderingTimer = new Timer((1000 / frame_rate), new ActionListener() {
    override def actionPerformed(e: ActionEvent) {
      update
      viewport.repaint()
    }
  })
  
  // Keyboard events
  def keyPressed(k: String) = {
    if (k == "w") {
      player.turnUp()
    }
    if (k == "s") {
      player.turnDown()
    }
    if (k == "a") {
      player.turnLeft()
    }
    if (k == "d") {
      player.turnRight()
    }
  }
  
  renderingTimer.start()
}

