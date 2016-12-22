package uusipeli

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer


class Jaa extends Item {
  
  setImage(Olutpullo.getImage())
  
  var slip = "../sounds/slip.mp3"
  var slipaani = new Media(slip);
  
  override def processCollision(p: Player) = {
    p.
    /*
     * Here we process the collision:
     * - we change player's state
     * - we play a sound
     */
    var mediaPlayer = new MediaPlayer(slipaani)
    mediaPlayer.play();
  }
}

object Olutpullo extends ItemStatic {
  imageFilename = "gfx/64 kalja.png"
  soundFilename = "sound/glass.wav"
}
