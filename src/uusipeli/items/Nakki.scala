package uusipeli.items

import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import uusipeli.items.Olutpullo
import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Player


class Nakki extends Item {
  
  setImage(Olutpullo.getImage())
  
  var nakki = "../sounds/nakki.mp3"
  var nakkiaani = new Media(nakki);
  
  override def processCollision(p: Player) = {
    this.active = false
    p.score -= 5
    /*
     * Here we process the collision:
     * - we change player's state
     * - we play a sound
     */
    var mediaPlayer = new MediaPlayer(nakkiaani)
    mediaPlayer.play();
  }
}

object Nakki extends ItemStatic {
  imageFilename = "gfx/64 nakki.png"
  soundFilename = "sounds/nakki.mp3"
}
