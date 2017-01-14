package uusipeli

import scala.swing._
import java.awt.Color
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer
import java.awt.image.BufferedImage
import uusipeli.model._
import uusipeli.levels._
import scala.collection.mutable.ArrayBuffer

/*
 * This object is the main application of the game.
 */
object Game {
  
  val window_width = WINDOW_WIDTH
  val window_height = WINDOW_HEIGHT
  val window_title = "Otaniemipeli"
  val frame_rate = 60
  
  /* Keys being held down */
  var key_w = false
  var key_s = false
  var key_a = false
  var key_d = false
  
  var started = false
  var paused = false
  var keysReversed = false
  
  var player = new Player
  val world = new World(player)
  var currLevel: Map = new LevelOne
  
  /* Effects, such as the "drunk effect" that last for a defined period of time. */
  var effects = ArrayBuffer[Effect]()
  
  /* Viewport to the world */
  val viewport = new Viewport(world, window_width, window_height, (window_width / 2), (window_height / 2))
  viewport.preferredSize = new Dimension(window_width, window_height)
  
  
  
  /* Starts the game. */
  def startGame(lvl: Map) = {
    currLevel = lvl
    world.loadMap(lvl)
    world.loadResources()
    this.started = true
    world.playMusic()
    renderingTimer.start()
  }
  
  /* Stops the game. */
  def stopGame() = {
    this.started = false
    world.stopMusic()
    renderingTimer.stop()
    viewport.reset()
    player.reset()
    world.reset()
    resetEffects()
  }
  
  /* Pauses the game. */
  def pauseGame() = {
    this.paused = true
    world.pauseMusic()
    renderingTimer.stop()
  }
  
  /* Continues a paused game. */
  def continueGame() = {
    this.paused = false
    world.continueMusic()
    renderingTimer.start()
  }
  
  /* This method is called when the pause key is pressed.
   * See Viewport.
   */
  def pauseKeyPressed() = {
    if (this.paused) {
      this.continueGame()
    } else {
      this.pauseGame()
    }
  }
  
  def newGameKeyPressed() = {
    if (this.started) {
      this.stopGame()
      this.startGame(new LevelOne)
    }
  }

  
  def top = new MainFrame {
    ignoreRepaint = true
    title = window_title
    size = new Dimension(window_width, window_height)
    background = Color.black
    viewport.preferredSize = new Dimension(window_width, window_height)
    
    contents = viewport
  }
  
  def update() = {
    if (this.started == true && this.paused != true) {
      processKeys()
      processEffects()
      player.update()
      // viewport.update()
      updateViewportLocation()
      player.checkCollisions(world.items)
      world.update()
    }
  }
  
  /* This method syncs the viewports location with player y coordinate.
   * This is activated when the player has reached the middle of the viewport. 
   */
  def updateViewportLocation() = {
    if (player.position_y > ((WINDOW_HEIGHT / 2) - 1)) {
      viewport.viewport_y = player.position_y.toInt
    }
  }
  
  // Timer: Here we set up a timer that updates the game state and calls viewport.repaint.
  val renderingTimer = new Timer((1000 / frame_rate), new ActionListener() {
    override def actionPerformed(e: ActionEvent) {
      update()
      viewport.repaint()
    }
  })
  
  def processKeys() {
    if (keysReversed == true) {
      /*
      if (key_w) {
        player.turnUp()
      }
      if (key_s) {
        player.turnDown()
      }
      */
      if (key_a) {
        player.turnRight()
      }
      if (key_d) {
        player.turnLeft()
      }  
    } else {
      /*
      if (key_w) {
        player.turnUp()
      }
      if (key_s) {
        player.turnDown()
      }
      */
      if (key_a) {
        player.turnLeft()
      }
      if (key_d) {
        player.turnRight()
      }
    }
  }
  
  // Keyboard events
  def keyPressed(k: String) = {
    if (k == "w") {
      key_w = true
    }
    if (k == "s") {
      key_s = true
    }
    if (k == "a") {
      key_a = true
    }
    if (k == "d") {
      key_d = true
    }
  }
  
  def keyReleased(k: String) = {
    if (k == "w") {
      key_w = false
    }
    if (k == "s") {
      key_s = false
    }
    if (k == "a") {
      key_a = false
    }
    if (k == "d") {
      key_d = false
    }
  }
  
  def addEffect(effect: Effect) = {
    effect.startTime = scala.compat.Platform.currentTime
    this.effects += effect
    effect.start()
  }
  
  def processEffects() = {
    val currTime = scala.compat.Platform.currentTime
    var oldEffects = ArrayBuffer[Effect]()
    this.effects.foreach { x =>
      if (currTime > (x.startTime + x.timeout)) {
        x.end()
        oldEffects += x
      }
    }
    
    /* Remove old effects from the array. */
    if (oldEffects.size > 0) {
      this.effects --= oldEffects
    }
  }
  
  def resetEffects() = {
    this.effects.foreach {
      x => {
        x.end()
      }
    }
    this.effects.clear()
  }
}
