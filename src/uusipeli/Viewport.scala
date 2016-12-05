package uusipeli

import scala.swing._
import scala.swing.event._
import java.awt.event._
import java.awt.image.BufferedImage
import java.awt.Color

class Viewport(world: World, viewport_width: Int, viewport_height: Int, var viewport_x: Int, var viewport_y: Int) extends Panel {
  this.background = Color.black
  
  val viewport_image = new BufferedImage(viewport_width, viewport_height, BufferedImage.TYPE_INT_ARGB)
  
  focusable = true
  
  requestFocus()
  
  listenTo(keys)
  
  reactions += {
    case KeyPressed(_, Key.W, _, _) => Game.keyPressed("w")
    case KeyPressed(_, Key.S, _, _) => Game.keyPressed("s")
    case KeyPressed(_, Key.A, _, _) => Game.keyPressed("a")
    case KeyPressed(_, Key.D, _, _) => Game.keyPressed("d")
  }
  
  def render() = {
    val viewport_graphics = viewport_image.getGraphics
    
    /* Fill the background. */
    viewport_graphics.clearRect(0, 0, viewport_width, viewport_height)
    
    /* First we draw the background image, if it exists. */
    if (world.background_image.isDefined) {
      viewport_graphics.drawImage(
          world.background_image.get,
          0,
          0,
          null)
    }
      
    /* Then we draw the items. */
      
    for (item <- world.items) {
      // Is this object visible?
      if (
        item.position_x > (viewport_x - viewport_width / 2)
        && item.position_x < (viewport_x + viewport_width / 2)
        && item.position_y > (viewport_y - viewport_height / 2)
        && item.position_x < (viewport_y + viewport_height / 2)) {
            
        viewport_graphics.drawImage(
            item.render,
            (viewport_width / 2) + (item.position_x - viewport_x) - (item.width / 2),
            (viewport_height / 2) + (item.position_y - viewport_y) - (item.height / 2),
            null)
      }
    }
      
    /* Finally, we draw the player. */
    viewport_graphics.drawImage(
            world.getPlayer.render,
            (viewport_width / 2) + (world.getPlayer.position_x.toInt - viewport_x) - (world.getPlayer.width / 2),
            (viewport_height / 2) + (world.getPlayer.position_y.toInt - viewport_y) - (world.getPlayer.height / 2),
            null)
  }

  override def paintComponent(g: Graphics2D) {
    render()
    g.drawImage(
      viewport_image,
      null,
      0,
      0)
  }
}
