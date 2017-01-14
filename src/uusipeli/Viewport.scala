package uusipeli

import scala.swing._
import scala.swing.event._
import java.awt.event._
import java.awt.image.BufferedImage
import java.awt.Color
import uusipeli.model._

/*
 * Viewport_x and viewport_y: The middle of the viewport in world coordinates.
 */

class Viewport(world: World, viewport_width: Int, viewport_height: Int, val viewport_start_x: Int, val viewport_start_y: Int) extends Panel {
  // Background color the the viewport.
  this.background = Color.black
  
  var viewport_x = viewport_start_x
  var viewport_y = viewport_start_y
  
  val viewport_image = new BufferedImage(viewport_width, viewport_height, BufferedImage.TYPE_INT_ARGB)
  
  // We set this component focusable and request focus, so we can react to keyboard events.
  focusable = true
  
  requestFocus()
  
  // Listen to keyboard events.
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
    
    case KeyTyped(_, 'P', _, _) => Game.pauseKeyPressed()
    case KeyTyped(_, 'p', _, _) => Game.pauseKeyPressed()
    /*
    case KeyTyped(_, 'N', _, _) => Game.newGameKeyPressed()
    case KeyTyped(_, 'n', _, _) => Game.newGameKeyPressed()    
  	*/
  }
  
  def render() = {
    val viewport_graphics = viewport_image.getGraphics
    
    val viewportUpperLeftXInWorldCoordinates = viewport_x - (viewport_width / 2)
    val viewportUpperLeftYInWorldCoordinates = viewport_y - (viewport_height / 2)
    
    /* Clear the buffer. */
    viewport_graphics.clearRect(0, 0, viewport_width, viewport_height)
    
    
    /* Draw slices and their items. */
    for (slice <- world.slices) {
      
      /* Is this slice visible? */
      if (
        (slice.index * slice.height) > (viewport_y - (viewport_height / 2))
        || (slice.index * slice.height) < (viewport_y + (viewport_height / 2))) {
        
        /* Draw the background of the slice.
         * 
         * Transform world coordinates into viewport coordinates.
         */
        viewport_graphics.drawImage(
            slice.background_image.get,
            0,
            slice.index * slice.height - (viewport_y - (viewport_height / 2)),
            null)
        
        /* Draw all visible items in this slice.
         * 
         * Slice coordinates -> world coordinates -> viewport coordinates.
         */
               
        // Item coordinates in world coordinate space.
        var itemXWorld, itemYWorld = 0

        // Item coordinates (the middle point of the item) in viewport coordinate space.
        // (0, 0) is the upper left corner of the viewport.
        var itemXViewport, itemYViewport = 0
        
        for (item <- slice.items) {
          if (item.visible == true) {
          
          // We assume that the slice is as wide as the world.
          itemXWorld = item.position_x
          itemYWorld = slice.index * SLICE_HEIGHT + item.position_y
          
          itemXViewport = itemXWorld 
          itemYViewport = itemYWorld - viewportUpperLeftYInWorldCoordinates
          
          // println("Rendered item. Local coordinates: (" + item.position_x + ", " + item.position_y + ") World coordinates: (" + itemXWorld + ", " + itemYWorld + ")")

            viewport_graphics.drawImage(
                item.render,
                itemXViewport - (item.width / 2),
                itemYViewport - (item.height / 2),
                null)
          }
        }
      }
    }
    
    /*
     * Player's coordinates (middle point of the player) in viewport coordinates, where (0, 0) is the upper left corner.
     */
    val playerXViewport = world.getPlayer.position_x.toInt - viewportUpperLeftXInWorldCoordinates
    val playerYViewport = world.getPlayer.position_y.toInt - viewportUpperLeftYInWorldCoordinates
    
    /* Draw player on top of the image. */
    viewport_graphics.drawImage(
            world.getPlayer.render,
            playerXViewport - (world.getPlayer.width / 2),
            playerYViewport - (world.getPlayer.height / 2),
            null)
            
    /* TODO: Draw the level name. */
    
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
    // viewport_y += viewport_speed // Scroll screen downwards
  }
  
  def reset() = {
    this.viewport_x = this.viewport_start_x
    this.viewport_y = this.viewport_start_y
  }
}
