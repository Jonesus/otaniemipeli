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
    case KeyReleased(_, Key.W, _, _) => Game.keyReleased("w")
    case KeyReleased(_, Key.S, _, _) => Game.keyReleased("s")
    case KeyReleased(_, Key.A, _, _) => Game.keyReleased("a")
    case KeyReleased(_, Key.D, _, _) => Game.keyReleased("d")
  }
  
  def render() = {
    val viewport_graphics = viewport_image.getGraphics
    
    /* Clear the buffer. */
    viewport_graphics.clearRect(0, 0, viewport_width, viewport_height)
    
    
    /* Draw items from slices. */
    for (slice <- world.slices) {
      // Is this object visible?
      if (
        slice.index * slice.height > (viewport_y - viewport_height)
        && slice.index * slice.height < (viewport_y + viewport_height)) {
            
        viewport_graphics.drawImage(
            slice.background_image.get,
            0,
            slice.index * slice.height - viewport_y,
            null)
        
        for (item <- slice.items) {
          if (item.active == true) {
            viewport_graphics.drawImage(
                item.render,
                item.position_x - (item.width / 2),
                slice.index * slice.height + (item.position_y - (item.height / 2)) - viewport_y,
                null)
          }
        }
      }
    }
    
    /* Draw player on top of the image. */
    viewport_graphics.drawImage(
            world.getPlayer.render,
            world.getPlayer.position_x.toInt,
            world.getPlayer.position_y.toInt - viewport_y,
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
  
  def update() = {
    viewport_y += 3  // Scroll screen downwards
  }
}
