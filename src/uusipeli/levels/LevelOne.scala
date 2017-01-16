package uusipeli.levels

import uusipeli.Item
import uusipeli.BaseLevel
import uusipeli.Slice
import uusipeli.model._
import uusipeli.items._

class LevelOne extends BaseLevel {
  length = 100
  background_music_filename = "sounds/pahkinansarkija.wav"
  level_title_filename = "gfx/title jmt.png"
  bg_files = List("gfx/bg1.png", "gfx/bg2.png", "gfx/bg3.png", "gfx/bg4.png")
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
  
  
  reset()  // Initialize level
}
