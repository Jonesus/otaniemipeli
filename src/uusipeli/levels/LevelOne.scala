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
  
  val rand = new scala.util.Random
  val bg_files = List("gfx/bg1.png", "gfx/bg2.png", "gfx/bg3.png", "gfx/bg4.png")
  
  /* Generate map slices */
  slices += new Slice("gfx/bg1.png")
  slices += new Slice("gfx/bg2.png")
  slices += new Slice("gfx/bg3.png")
  
  for (i <- 3 to 50) {
    slices += new Slice(bg_files(rand.nextInt(bg_files.length)))
    slices(i).index = i
    slices(i).populate
    items = items ++ slices(i).items
  }
}
