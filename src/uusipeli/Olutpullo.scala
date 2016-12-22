package uusipeli

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File


class Olutpullo extends Item {
  
  setImage(Olutpullo.getImage())
  
  override def processCollision(p: Player) = {
    /*
     * Here we process the collision:
     * - we change player's state
     * - we play a sound
     */
  }
}

object Olutpullo extends ItemStatic {
  imageFilename = "gfx/64 kalja.png"
  soundFilename = "sound/glass.wav"
}
