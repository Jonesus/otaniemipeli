package uusipeli.levels

import uusipeli.Map
import uusipeli.Slice
import uusipeli.model._
import uusipeli.items._

class LevelOne extends Map {
  /* Dimensions of this level. */
  width = WINDOW_WIDTH
  height = 1200
  
  /* Player's starting position. */
  player_position_x = width / 2
  player_position_y = 100
  
  background_image_filename = "gfx/level1_background.png"
  background_music_filename = "sounds/pahkinansarkija.wav"
  
  val rand = new scala.util.Random
  val bg_files = List("gfx/bg1.png", "gfx/bg2.png", "gfx/bg3.png", "gfx/bg4.png")
  
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
    
    /* Add level name item to the third slice. */
    slices(2).items += new LevelNameItem("gfx/title jmt.png")
    slices(2).items(0).position_x = WINDOW_WIDTH / 2
    
    for (i <- 3 to 50) {
      slices += new Slice(bg_files(rand.nextInt(bg_files.length)))
      slices(i).index = i
      slices(i).populate
      items = items ++ slices(i).items
    }
  }
  
  reset()
}
