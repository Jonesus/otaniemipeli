package uusipeli

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
 
  
  items += new Ball
  items += new Ball
  
  items(0).position_x = 300
  items(0).position_y = 350
  
  items(1).position_x = 340
  items(1).position_y = 280  
}
