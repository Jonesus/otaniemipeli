package uusipeli.items

import javafx.scene.media.Media
import uusipeli.effects.BeerEffect
import uusipeli.Game
import uusipeli.Item
import uusipeli.ItemStatic
import uusipeli.Player


class Olutpullo extends Item {
  
  setImage(Olutpullo.getImage())
  
  var olut = "../sounds/olutpullo.mp3"
  var aani = new Media(olut);
  
  override def processCollision(p: Player) = {
    this.active = 0
    /*
     * Here we process the collision:
     * - we change player's state
     * - we play a sound
     */
    /*
    var mediaPlayer = new MediaPlayer(aani)
    mediaPlayer.play();
    */
    Game.addEffect(new BeerEffect())
  }
}

object Olutpullo extends ItemStatic {
  imageFilename = "gfx/64 kalja.png"
  soundFilename = "sounds/olutpullo.mp3"
}
