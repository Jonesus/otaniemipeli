package uusipeli.levels

import uusipeli.Item
import uusipeli.BaseLevel
import uusipeli.Slice
import uusipeli.Game
import uusipeli.model._
import uusipeli.items._

class LevelThree extends BaseLevel {
  
  length = 200
  speed_bonus = 4
  background_music_filename = "sounds/pahkinansarkija.wav"
  level_title_filename = "gfx/title smt.png"
  bg_files = List("gfx/tl1.png", "gfx/tl2.png", "gfx/tl3.png", "gfx/tl4.png")
  level_goal_filename = "gfx/maali1.png"
  
  
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
  
  
  reset()
}
