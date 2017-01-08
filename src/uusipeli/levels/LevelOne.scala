package uusipeli.levels

import uusipeli.Map
import uusipeli.Slice

class LevelOne extends Map {
  /* Dimensions of this level. */
  width = 1200
  height = 1200
  
  /* Player's starting position. */
  player_position_x = 300
  player_position_y = 300
  
  /* Background image file. */
  background_image_filename = "gfx/level1_background.png"
  
  /* Background music file. */
  background_music_filename = "music/level1_music.wav"
  
  /* Generate map slices */
  val rand = new scala.util.Random
  val bg_files = List("gfx/bg1.png", "gfx/bg2.png", "gfx/bg3.png", "gfx/bg4.png")

  var i = 0
  for (i <- 0 to 100) {
    val filename : String = bg_files(rand.nextInt(bg_files.length))
    slices += new Slice(filename)
    slices(i).index = i
    slices(i).populate
    items = items ++ slices(i).items
  }
}
