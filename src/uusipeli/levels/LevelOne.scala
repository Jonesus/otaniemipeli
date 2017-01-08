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
  slices += new Slice
  slices += new Slice
  slices += new Slice
  for (i <- 3 to 20) {
    slices += new Slice
    slices(i).index = i
    slices(i).populate
    items = items ++ slices(i).items
  }
}
