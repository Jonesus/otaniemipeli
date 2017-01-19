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
  
  /* Toggle this true to enable render debug drawing. */
  val renderDebug = false
  
  /* Create the info bar that draws the player's health and points. */
  val infoBar = new InfoBar

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
    case KeyPressed(_, Key.Left, _, _) => Game.keyPressed("left")
    case KeyPressed(_, Key.Right, _, _) => Game.keyPressed("right")

    case KeyReleased(_, Key.W, _, _) => Game.keyReleased("w")
    case KeyReleased(_, Key.S, _, _) => Game.keyReleased("s")
    case KeyReleased(_, Key.A, _, _) => Game.keyReleased("a")
    case KeyReleased(_, Key.D, _, _) => Game.keyReleased("d")
    case KeyReleased(_, Key.Left, _, _) => Game.keyReleased("left")
    case KeyReleased(_, Key.Right, _, _) => Game.keyReleased("right")

    case KeyTyped(_, 'P', _, _) => Game.pauseKeyPressed()
    case KeyTyped(_, 'p', _, _) => Game.pauseKeyPressed()
  }

  def render() = {
    val viewport_graphics = viewport_image.getGraphics

    val viewportUpperLeftXInWorldCoordinates = viewport_x - (viewport_width / 2)
    val viewportUpperLeftYInWorldCoordinates = viewport_y - (viewport_height / 2)
    
    // Item coordinates in world coordinate space.
    var itemXWorld, itemYWorld = 0

    // Item coordinates (the middle point of the item) in viewport coordinate space.
    // (0, 0) is the upper left corner of the viewport.
    var itemXViewport, itemYViewport = 0

    /* Clear the buffer. */
    viewport_graphics.clearRect(0, 0, viewport_width, viewport_height)

    /* Draw slices and their items. */
    for (slice <- world.slices) {

      /* Is this slice visible? */
      if ((slice.index * slice.height) > (viewport_y - (viewport_height / 2))
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

        for (item <- slice.items) {
          if (item.visible == true) {

            // We assume that the slice is as wide as the world.
            itemXWorld = item.position_x
            itemYWorld = slice.index * SLICE_HEIGHT + item.position_y

            itemXViewport = itemXWorld
            itemYViewport = itemYWorld - viewportUpperLeftYInWorldCoordinates

            if (this.renderDebug == true) {
              viewport_graphics.drawRect(itemXViewport - item.width / 2, itemYViewport - item.height / 2, item.width, item.height);
            }
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
    
    if (this.renderDebug == true) {
      viewport_graphics.drawRect(playerXViewport - (world.getPlayer.width / 2), playerYViewport - (world.getPlayer.height / 2), world.getPlayer.width, world.getPlayer.width);
    }
    
    /* Draw player on top of the image. */
    viewport_graphics.drawImage(
      world.getPlayer.render,
      playerXViewport - (world.getPlayer.width / 2),
      playerYViewport - (world.getPlayer.height / 2),
      null)

    /* Here the InfoBar object is used to draw the points indicator (dice) and player health (hearts). */

    viewport_graphics.drawImage(
      infoBar.dice,
      5,
      5,
      null)

    viewport_graphics.drawImage(
      infoBar.drawHealthbar(),
      5,
      42,
      null)
      
      /* Draw the numbers directly on the image (for performance reasons). */
      infoBar.drawPoints(viewport_graphics.asInstanceOf[Graphics2D])
  }

  //Draw the players points next to the points indicator (noppa).
  override def paintComponent(g: Graphics2D) {
    render()
    g.drawImage(
      viewport_image,
      null,
      0,
      0)
  }

  def reset() = {
    this.viewport_x = this.viewport_start_x
    this.viewport_y = this.viewport_start_y
  }
}
