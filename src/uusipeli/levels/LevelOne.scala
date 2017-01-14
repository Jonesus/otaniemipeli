package uusipeli.levels

import uusipeli.Map
import uusipeli.Slice
import uusipeli.model._

class LevelOne extends Map {
  /* Dimensions of this level. */
  width = WINDOW_WIDTH
  height = 1200
  
  /* Player's starting position. */
  player_position_x = width / 2
  player_position_y = 200
  
  /* Background image file. */
  background_image_filename = "gfx/level1_background.png"
  
  /* Background music file. */
  background_music_filename = "sounds/pahkinansarkija.wav"
  
  val rand = new scala.util.Random
  val bg_files = List("gfx/bg1.png", "gfx/bg2.png", "gfx/bg3.png", "gfx/bg4.png")
  
  // Test object
  /*
  var noppa = new uusipeli.items.Noppa()
  noppa.position_x = 0
  noppa.position_y = 0

  slices(0).items += noppa
	*/
  
  def reset() = {
    /* Generate map slices */
    slices.clear()
    
    /* First add empty slices. */
    slices += new Slice("gfx/bg1.png")
    slices += new Slice("gfx/bg2.png")
    slices += new Slice("gfx/bg3.png")
  
    slices(0).index = 0
    slices(1).index = 1
    slices(2).index = 2
    
    for (i <- 3 to 50) {
      slices += new Slice(bg_files(rand.nextInt(bg_files.length)))
      slices(i).index = i
      slices(i).populate
      items = items ++ slices(i).items
    }
  }
  
  reset()
}
