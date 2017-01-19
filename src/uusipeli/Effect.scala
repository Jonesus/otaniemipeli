package uusipeli

/* This class represents effects, such as BeerEffect, that affect the game's state for a while. */
/* Effects are processed in the game loop. */
abstract class Effect {
  /* When was this effect started? */
  var startTime: Long = 0
  var delay = 0
  var started = false
  
  def start() = {
    /* Code to run when the effect is added to the queue. */
  }
  
  def end() = {
    /* Code to run when delay has passed. */
  }
}
