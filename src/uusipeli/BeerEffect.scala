package uusipeli

class BeerEffect extends Effect {
  timeout = 800
  def start()={
    Game.keysReversed = true
  }
  
  def end()={
    Game.keysReversed = false
  }
}