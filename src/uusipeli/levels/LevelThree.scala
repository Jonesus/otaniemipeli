package uusipeli.levels

import uusipeli.Item
import uusipeli.BaseLevel
import uusipeli.Slice
import uusipeli.Game
import uusipeli.model._
import uusipeli.items._

class LevelThree extends BaseLevel {
  
  length = 200
  background_music_filename = "sounds/pahkinansarkija.wav"
  level_title = "gfx/title smt.png"
  bg_files = List("gfx/tl1.png", "gfx/tl2.png", "gfx/tl3.png", "gfx/tl4.png")
  
  
  override def randomItem(): Option[Item] = {
    val itemType = rand.nextInt(7)
    if (itemType == 0) return Some(new Olutpullo())
    if (itemType == 1) return Some(new Noppa())
    if (itemType == 2) return Some(new Nakki())
    if (itemType == 3) return Some(new Jaa())
    if (itemType == 4) return Some(new Kivi())
    if (itemType == 5) return Some(new Prujut())
    if (itemType == 6) return Some(new Spagetti())
    None
  }
  
  
  reset()
}
