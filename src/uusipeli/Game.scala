package uusipeli

import scala.swing._
import java.awt.Color
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer
import java.awt.image.BufferedImage
import uusipeli.model._
import uusipeli.levels._
import uusipeli.items.GameOverItem
import uusipeli.items.YouWinItem
import scala.collection.mutable.ArrayBuffer

/*
 * This object is the game functionality.
 */
object Game {

  val windowWidth = WINDOW_WIDTH
  val windowHeight = WINDOW_HEIGHT
  val windowTitle = "Otaniemipeli"
  val frameRate = 60
  
  /* A callback function that will show the main menu. */
  private var showMenuCallback: () => Any = null
  
  /* This sets the callback. */
  def setMenuCallback(f: => Any) = {
    this.showMenuCallback = () => f
  }
  
  /* Keys being held down */
  var key_a = false
  var key_d = false

  /* Boolean flags for game's current state. */
  var started = false
  var paused = false
  var keysReversed = false
  
  /* Flag that indicates that the game should be exited. */
  var exitToMenuCommand = false
  
  /* Player object. */
  var player = new Player
  
  /* World object. */
  val world = new World(player)
    
  /* Events that are processed once. */
  var events = ArrayBuffer[Event]()
  
  /* Effects, such as the "drunk effect" that last for a defined period of time. */
  var effects = ArrayBuffer[Effect]()

  /* Viewport to the world. */
  val viewport = new Viewport(world, windowWidth, windowHeight, (windowWidth / 2), (windowHeight / 2))
  viewport.preferredSize = new Dimension(windowWidth, windowHeight)

  /* Starts the game with level lvl. */
  def startGame(lvl: BaseLevel) = {
    /* First, reset the game state. */
    this.exitToMenuCommand = false
    this.paused = false
    this.keysReversed = false
    this.started = true
    this.resetEffects()
    
    /* Reset the game world and load the level. */
    this.world.reset()
    this.world.loadLevel(lvl)
    this.world.loadResources()
    
    /* Reset the viewport. */
    this.viewport.reset()
    
    /* Start playing the background music. */
    this.world.playMusic()
    
    /* Start the game loop. */
    this.gameLoopTimer.start()
  }

  /* Stops the game. */
  def stopGame() = {
    gameLoopTimer.stop()
    this.started = false
    
    this.events.clear()
    this.effects.clear()

    world.stopMusic()

    player.reset()
    world.reset()
    viewport.reset()
  }

  /* Pauses the game. */
  def pauseGame() = {
    this.paused = true
    world.pauseMusic()
    gameLoopTimer.stop()
  }

  /* Continues a paused game. */
  def continueGame() = {
    this.paused = false
    world.continueMusic()
    gameLoopTimer.start()
  }
  
  /* Exits the game. Note: This is called from update().*/
  def exitGameToMenu() = {
    this.stopGame()
    this.showMainMenu()
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
  
  /* Calls the callback. */
  def showMainMenu(): Unit = {
    if (this.showMenuCallback == null) {
      return
    }
    
    this.showMenuCallback()
  }
  
  /* Shows "Game over" text to the user. */
  def showGameOver() = {
    val i = new GameOverItem()
    this.viewport.overlayImage = Some(i.image)
  }
  
  /* Shows "You win" text to the user. */
  def showYouWin() = {
    val i = new YouWinItem()
    this.viewport.overlayImage = Some(i.image)
  }  
  
  /* This method is run every time the game loop fires an event. */
  def update(): Unit = {
    if (this.started == true && this.paused != true) {
      processEvents()
      processEffects()
      
      processKeys()
      
      world.checkPlayerCollisions()
      world.checkPlayerDeath()
      
      player.update()
      updateViewportLocation()
      world.update()
      this.checkExitGameCommand()
    }
  }
  
  /* Exits the game if the flag is set. */
  def checkExitGameCommand() = {
    this.synchronized {
      if (this.exitToMenuCommand == true) {
        this.stopGame()
        this.exitGameToMenu()
      }
    }
  }

  /* This method syncs the viewport's y coordinate with the player's y coordinate.
   * This is activated when the player has reached START_VIEWPORT_SCROLL.
   */
  def updateViewportLocation() = {
    if (player.positionY >= START_VIEWPORT_SCROLL) {
      viewport.viewportY = player.positionY.toInt + VIEWPORT_SCROLL_OFFSET
    }
  }

  /* Set up a timer that updates the game state and calls viewport.repaint. */
  val gameLoopTimer = new Timer((1000 / frameRate), new ActionListener() {
    override def actionPerformed(e: ActionEvent) {
      update()
      viewport.repaint()
    }
  })

  /* Processes keys. */
  def processKeys() {
    if (keysReversed == true) {
      if (key_a) {
        player.turnRight()
      }
      if (key_d) {
        player.turnLeft()
      }
    } else {
      if (key_a) {
        player.turnLeft()
      }
      if (key_d) {
        player.turnRight()
      }
    }
  }

  /* Event handlers for keyboard events. See Viewport. */
  def keyPressed(k: String) = {
    if (k == "a") {
      key_a = true
    }
    if (k == "d") {
      key_d = true
    }
    if (k == "left") {
      key_a = true
    }
    if (k == "right") {
      key_d = true
    }
  }

  def keyReleased(k: String) = {
    if (k == "a") {
      key_a = false
    }
    if (k == "d") {
      key_d = false
    }
    if (k == "left") {
      key_a = false
    }
    if (k == "right") {
      key_d = false
    }
  }

  /* Adds a new effect to the queue.
   * Note: Only one particular effect (for example one BeerEffect) can be running at the same time.
   * The old effect is replaced with the new effect.
   */
  def addEffect(effect: Effect) = {
    var oldEffects = ArrayBuffer[Effect]()
    effect.startTime = scala.compat.Platform.currentTime
    
    this.effects.filter { x => x.getClass() == effect.getClass() }.foreach { x =>
      oldEffects += x
    }
    
    if (oldEffects.size > 0) this.effects --= oldEffects
    this.effects += effect
    
    effect.start()
  }
  
  def addEvent(event: Event) = {
    this.events += event
  }  

  /* This method processes the effects:
   * - If delay has passed, end() is called and the effect is removed from the queue.
   */
  def processEffects() = {
    val currTime = scala.compat.Platform.currentTime
    var oldEffects = ArrayBuffer[Effect]()
    
    this.effects.foreach { x =>
      if (x.started == false) {
        x.startTime = scala.compat.Platform.currentTime
        x.start()
        x.started = true
      } else {
        if (currTime > (x.startTime + x.delay)) {
          x.end()
          oldEffects += x
        }
      }
    }

    /* Remove old effects from the array. */
    if (oldEffects.size > 0) {
      this.effects --= oldEffects
    }
  }
  
  /* This methods processes the events. */
  def processEvents() = {
	  val currTime = scala.compat.Platform.currentTime
	  val oldEvents = ArrayBuffer[Event]()
	  
	  this.events.foreach { x =>
	    if (x.started == false) {
		    x.startTime = scala.compat.Platform.currentTime
		    x.start()
			  x.started = true
		  } else {
	      if (currTime > (x.startTime + x.delay)) {
			    if (x.ended == false) {
				    x.ended = true
					  x.end()
					  oldEvents += x
				  }
			  }
		  }
	  }
	  
    /* Remove old effects from the array. */
    if (oldEvents.size > 0) {
      this.events --= oldEvents
    }	  
  }

  /* This methods ends all running effects. */
  def resetEffects() = {
    this.effects.foreach {
      x =>
        {
          x.end()
        }
    }
    
    /* Empty the queue. */
    this.effects.clear()
  }
}
