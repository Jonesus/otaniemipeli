package uusipeli.levels

import uusipeli.Item
import uusipeli.BaseLevel
import uusipeli.Slice
import uusipeli.model._
import uusipeli.items._

class LevelOne extends BaseLevel {
  length = 100
  background_music_filename = "sounds/pahkinansarkija.wav"
  level_title = "gfx/title jmt.png"
  bg_files = List("gfx/bg1.png", "gfx/bg2.png", "gfx/bg3.png", "gfx/bg4.png")

  
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
  
  
  reset()  // Initialize level
}
