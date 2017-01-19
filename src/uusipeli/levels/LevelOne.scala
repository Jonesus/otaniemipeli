package uusipeli.levels

import uusipeli.Item
import uusipeli.BaseLevel
import uusipeli.Slice
import uusipeli.Game
import uusipeli.model._
import uusipeli.items._
import uusipeli.Animation
import scala.collection.mutable.ArrayBuffer


class LevelOne extends BaseLevel {
  length = 100
  levelSpeedBonus = 0
  backgroundMusicFilename = "sounds/pahkinansarkija.wav"
  levelTitleFilename = "gfx/title jmt.png"
  bgFiles = List("gfx/bg1.png", "gfx/bg2.png", "gfx/bg3.png", "gfx/bg4.png")
  levelGoalItem = "gfx/128 maali1.png"
  
  diceCount = 15
  cheatsheetCount = 1
  spaghettiCount = 3
  beerCount = 30
  stoneCount = 20
  iceCount = 5
  sausageCount = 5
  maxScore = 20
  
  soberPlayerImageRightFilename = "gfx/128 fuksi oikea.png"
  soberPlayerImageLeftFilename = "gfx/128 fuksi vasen.png"
  drunkenPlayerImageRightFilename = "gfx/128 fuksi kanni oikea.png"
  drunkenPlayerImageLeftFilename = "gfx/128 fuksi kanni vasen.png"
  deadPlayerImageRightFilename = "gfx/128 fuksi dead oikea.png"
  deadPlayerImageFeftFilename = "gfx/128 fuksi dead vasen.png"
    
  winAnimation = new Animation
  winAnimation.frameDuration = 600
  winAnimation.addFrame("gfx/128 pixel teekkari oikea.png")
  winAnimation.addFrame("gfx/128 fuksi oikea.png")
  winAnimation.addFrame("gfx/128 pixel teekkari oikea.png")
  winAnimation.addFrame("gfx/128 fuksi oikea.png")
  winAnimation.addFrame("gfx/128 pixel teekkari oikea.png")
  winAnimation.addFrame("gfx/128 pixel teekkari oikea.png")
  winAnimation.addFrame("gfx/128 pixel teekkari oikea.png")
  winAnimation.addFrame("gfx/128 pixel teekkari oikea.png")
}
