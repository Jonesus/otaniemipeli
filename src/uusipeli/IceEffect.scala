package uusipeli

class IceEffect extends Effect {
  timeout = 400
  def start(player: Player)={
    player.acceleration = 2
  }
  
  def end(player: Player)={
    player.acceleration = 1
  }
}