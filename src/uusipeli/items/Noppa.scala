package uusipeli.items

import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import uusipeli.items.Olutpullo
import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Player


class Noppa extends Item {
  
  setImage(Olutpullo.getImage())
  
  var noppa = "../sounds/noppa.mp3"
  var noppaaani = new Media(noppa);
  
  override def processCollision(p: Player) = {
    this.active = 0
    p.score += 1
    /*
     * Here we process the collision:
     * - we change player's state
     * - we play a sound
     */
    var mediaPlayer = new MediaPlayer(noppaaani)
    mediaPlayer.play();
  }
}

object Noppa extends ItemStatic {
  imageFilename = "gfx/64 noppa.png"
  soundFilename = "sounds/noppa.mp3"
}
