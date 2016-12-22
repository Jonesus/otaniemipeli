package uusipeli

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer


class Nakki extends Item {
  
  setImage(Olutpullo.getImage())
  
  var nakki = "../sounds/nakki.mp3"
  var nakkiaani = new Media(nakki);
  
  override def processCollision(p: Player) = {
    this.active = 0
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
