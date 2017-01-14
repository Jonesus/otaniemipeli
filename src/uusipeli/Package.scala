package uusipeli

/* Game-wide settings */
package object model {
  val WINDOW_WIDTH = 1024
  val WINDOW_HEIGHT = 700
  val SLICE_HEIGHT = 128
  val SLICE_WIDTH = 1024
  val WALL_WIDTH = 100
  
  /* Speed: pixels / frame */
  val PLAYER_SPEED_DOWN = 3.0
  
  /* When will viewport start scrolling? */
  val START_VIEWPORT_SCROLL = 200
  
  val VIEWPORT_SCROLL_OFFSET = (this.WINDOW_HEIGHT / 2) - this.START_VIEWPORT_SCROLL
}
