package uusipeli

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

  var i = 0
  for (i <- 0 to 20) {
    slices += new Slice
    slices(i).index = i
    slices(i).populate
    items = items ++ slices(i).items
  }
}
