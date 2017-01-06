package uusipeli.items

import uusipeli.effects.BeerEffect
import uusipeli.Game
import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Player
import java.io.InputStream
import java.io.ByteArrayInputStream
import java.io.File
import java.nio.file.Files


class Olutpullo extends Item {
  
  setImage(Olutpullo.getImage())
  Olutpullo.loadSound()
  
  override def processCollision(p: Player) = {
    this.active = false
    /*
     * Here we process the collision:
     * - we change player's state
     * - we play a sound
     **/
    
    Olutpullo.playSound()
    
    Game.addEffect(new BeerEffect())
  }
}

object Olutpullo extends ItemStatic {
  imageFilename = "gfx/64 kalja.png"
  soundFilename = "sounds/olutpullo.mp3"
  var soundByteArray: Option[Array[Byte]] = None
  var mp3Player: Option[javazoom.jl.player.Player] = None
  
  def loadSound(): Unit = {
    if (soundByteArray.isDefined) return
    
    try {
      soundByteArray = Some(Files.readAllBytes(new File(soundFilename).toPath()))
      mp3Player = Some(new javazoom.jl.player.Player(new ByteArrayInputStream(soundByteArray.get)))
    } catch {
      case e: Exception => println("Could not read sound file " + soundFilename + ": " + e.toString())
    }
  }
  
  def playSound() = {
    try {
      mp3Player.get.play()
      mp3Player.get.close()
    } catch {
      case e: Exception => println("Error: Could not play sound file " + soundFilename + ": " + e.toString())
    }
  }
}

