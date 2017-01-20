package uusipeli.levels

import uusipeli.Item
import uusipeli.BaseLevel
import uusipeli.Slice
import uusipeli.Game
import uusipeli.model._
import uusipeli.items._
import uusipeli.Animation

class LevelThree extends BaseLevel {
  length = 200
  levelSpeedBonus = 4
  backgroundMusicFilename = "sounds/level3.wav"
  levelTitleFilename = "gfx/title otakaari.png"
  bgFiles = List("gfx/tl1.png", "gfx/tl2.png", "gfx/tl3.png", "gfx/tl4.png")
  levelGoalItem = "gfx/128 maali3.png"
  
  diceCount = 25
  cheatsheetCount = 3
  spaghettiCount = 8
  beerCount = 30
  stoneCount = 30
  iceCount = 60
  sausageCount = 15
  maxScore = 40
  
  soberPlayerImageRightFilename = "gfx/128 wanha oikea.png"
  soberPlayerImageLeftFilename = "gfx/128 wanha vasen.png"
  drunkenPlayerImageRightFilename = "gfx/128 wanha kanni oikea.png"
  drunkenPlayerImageLeftFilename = "gfx/128 wanha kanni vasen.png"
  deadPlayerImageRightFilename = "gfx/128 wanha dead oikea.png"
  deadPlayerImageLeftFilename = "gfx/128 wanha dead vasen.png"  
  
  winAnimation = new Animation
  winAnimation.addFrame("gfx/128 wanha dead oikea.png")
}
