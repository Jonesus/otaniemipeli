package uusipeli.items

import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Player


class Jaa extends Item {
  
  setImage(Jaa.getImage())
  
  var slip = "../sounds/slip.mp3"
  var slipaani = new Media(slip);
  
  override def processCollision(p: Player) = {
    
    /*
     * Here we process the collision:
     * - we change player's state
     * - we play a sound
     */
    var mediaPlayer = new MediaPlayer(slipaani)
    mediaPlayer.play();
  }
}

object Jaa extends ItemStatic {
  imageFilename = "gfx/64 jaa.png"
  soundFilename = "sounds/slip.mp3"
}
