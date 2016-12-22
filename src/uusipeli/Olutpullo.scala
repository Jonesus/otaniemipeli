package uusipeli

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer


class Olutpullo extends Item {
  
  setImage(Olutpullo.getImage())
  
  //var olut = "../sounds/olutpullo.mp3"
  var aani = new Media(soundFilename);
  
  override def processCollision(p: Player) = {
    this.active = 0
    /*
     * Here we process the collision:
     * - we change player's state
     * - we play a sound
     */
    var mediaPlayer = new MediaPlayer(aani)
    mediaPlayer.play();
  }
}

object Olutpullo extends ItemStatic {
  imageFilename = "gfx/64 kalja.png"
  soundFilename = "sound/olutpullo.mp3"
}
