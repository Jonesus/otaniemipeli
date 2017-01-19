package uusipeli.levels

import uusipeli.Item
import uusipeli.BaseLevel
import uusipeli.Slice
import uusipeli.Game
import uusipeli.model._
import uusipeli.items._

class LevelThree extends BaseLevel {
  
  length = 200
  levelSpeedBonus = 4
  backgroundMusicFilename = "sounds/pahkinansarkija.wav"
  levelTitleFilename = "gfx/title otakaari.png"
  bgFiles = List("gfx/tl1.png", "gfx/tl2.png", "gfx/tl3.png", "gfx/tl4.png")
  levelGoalItem = "gfx/128 maali3.png"
  
  soberPlayerImageRightFilename = "gfx/128 wanha oikea.png"
  soberPlayerImageLeftFilename = "gfx/128 wanha vasen.png"
  drunkenPlayerImageRightFilename = "gfx/128 wanha kanni oikea.png"
  drunkenPlayerImageLeftFilename = "gfx/128 wanha kanni vasen.png"
  deadPlayerImageRightFilename = "gfx/128 wanha dead oikea.png"
  deadPlayerImageFeftFilename = "gfx/128 wanha dead vasen.png"  
  
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
