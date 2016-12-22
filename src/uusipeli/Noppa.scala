package uusipeli

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer


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

object Olutpullo extends ItemStatic {
  imageFilename = "gfx/64 kalja.png"
  soundFilename = "sound/glass.wav"
}
