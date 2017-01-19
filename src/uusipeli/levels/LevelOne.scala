package uusipeli.levels

import uusipeli.Item
import uusipeli.BaseLevel
import uusipeli.Slice
import uusipeli.Game
import uusipeli.model._
import uusipeli.items._

class LevelOne extends BaseLevel {
  length = 100
  level_speed_bonus = 0
  background_music_filename = "sounds/pahkinansarkija.wav"
  level_title_filename = "gfx/title jmt.png"
  bg_files = List("gfx/bg1.png", "gfx/bg2.png", "gfx/bg3.png", "gfx/bg4.png")
  level_goal_filename = "gfx/maali1.png"
  
  sober_player_image_right_filename = "gfx/128 fuksi oikea.png"
  sober_player_image_left_filename = "gfx/128 fuksi vasen.png"
  drunken_player_image_right_filename = "gfx/128 fuksi kanni oikea.png"
  drunken_player_image_left_filename = "gfx/128 fuksi kanni vasen.png"
  dead_player_image_right_filename = "gfx/128 fuksi dead oikea.png"
  dead_player_image_left_filename = "gfx/128 fuksi dead vasen.png"
    
  override def randomItem(): Option[Item] = {
    val itemType = rand.nextInt(7)
    if (itemType == 0) return Some(new Beer())
    if (itemType == 1) return Some(new Dice())
    if (itemType == 2) return Some(new Sausage())
    if (itemType == 3) return Some(new Ice())
    if (itemType == 4) return Some(new Stone())
    if (itemType == 5) return Some(new Cheatsheet())
    if (itemType == 6) return Some(new Spaghetti())
    None
  }
}
