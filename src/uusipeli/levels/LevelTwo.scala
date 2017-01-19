package uusipeli.levels

import uusipeli.Item
import uusipeli.BaseLevel
import uusipeli.Game
import uusipeli.Slice
import uusipeli.model._
import uusipeli.items._

class LevelTwo extends BaseLevel {
  length = 150
  level_speed_bonus = 2
  background_music_filename = "sounds/pahkinansarkija.wav"
  level_title_filename = "gfx/title smt.png"
  bg_files = List("gfx/mt1.png", "gfx/mt2.png", "gfx/mt3.png", "gfx/mt4.png")
  level_goal_filename = "gfx/maali2.png"
  
  sober_player_image_right_filename = "gfx/128 pixel teekkari oikea.png"
  sober_player_image_left_filename = "gfx/128 pixel teekkari vasen.png"
  drunken_player_image_right_filename = "gfx/128 teekkari kanni oikea.png"
  drunken_player_image_left_filename = "gfx/128 teekkari kanni vasen.png"
  dead_player_image_right_filename = "gfx/128 teekkari dead oikea.png"
  dead_player_image_left_filename = "gfx/128 teekkari dead vasen.png"
  
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
