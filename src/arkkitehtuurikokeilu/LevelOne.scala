package arkkitehtuurikokeilu

class LevelOne extends Map {
  /* Dimensions of this level. */
  
  width = 1200
  height = 1200
  
  /* Player's starting position. */
  player_position_x = 300
  player_position_y = 100
  
  /* Background image file. */
  background_image_filename = "taustakuva.png"
  
  /* Background music file. */
  background_music_filename = "taustamusiikki.wav"
  
  /* Here we add items to this map. */
  
  val b = new Ball
  b.position_x = 300
  b.position_y = 300
  
  items += b
}
