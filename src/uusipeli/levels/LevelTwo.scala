package uusipeli.levels

import uusipeli.Item
import uusipeli.BaseLevel
import uusipeli.Game
import uusipeli.Slice
import uusipeli.model._
import uusipeli.items._
import uusipeli.Animation

class LevelTwo extends BaseLevel {
  length = 150
  levelSpeedBonus = 2
  backgroundMusicFilename = "sounds/pahkinansarkija.wav"
  levelTitleFilename = "gfx/title smt.png"
  bgFiles = List("gfx/mt1.png", "gfx/mt2.png", "gfx/mt3.png", "gfx/mt4.png")
  levelGoalItem = "gfx/128 maali2.png"
  
  diceCount = 20
  cheatsheetCount = 2
  spaghettiCount = 5
  beerCount = 20
  stoneCount = 50
  iceCount = 10
  sausageCount = 10
  
  soberPlayerImageRightFilename = "gfx/128 pixel teekkari oikea.png"
  soberPlayerImageLeftFilename = "gfx/128 pixel teekkari vasen.png"
  drunkenPlayerImageRightFilename = "gfx/128 teekkari kanni oikea.png"
  drunkenPlayerImageLeftFilename = "gfx/128 teekkari kanni vasen.png"
  deadPlayerImageRightFilename = "gfx/128 teekkari dead oikea.png"
  deadPlayerImageFeftFilename = "gfx/128 teekkari dead vasen.png"
  
  winAnimation = new Animation
  winAnimation.frameDuration = 600
  winAnimation.addFrame("gfx/128 wanha oikea.png")
  winAnimation.addFrame("gfx/128 pixel teekkari oikea.png")
  winAnimation.addFrame("gfx/128 wanha oikea.png")
  winAnimation.addFrame("gfx/128 pixel teekkari oikea.png")
  winAnimation.addFrame("gfx/128 wanha oikea.png")
  winAnimation.addFrame("gfx/128 wanha oikea.png")
  winAnimation.addFrame("gfx/128 wanha oikea.png")
  winAnimation.addFrame("gfx/128 wanha oikea.png")
}
