package uusipeli

import scala.collection.mutable.ArrayBuffer
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.File
import scala.util.Random
import uusipeli.items._
import uusipeli.model._

/* This class represents a slice. The game world is composed of slices that hold items. */
class Slice(filename: String) {
  val rand = new scala.util.Random

  /* Vertical index of the slice. */
  var index = 0
  
  val width = SLICE_WIDTH
  val height = SLICE_HEIGHT
  
  /* Items of this slice. */
  var items = ArrayBuffer[Item]()
  
  /* Slice background. */
  val backgroundImage = Some(ImageIO.read(new File(filename)))
  
  /* Renders the slice. */
  def render: BufferedImage = {
    new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
  }
  
  /* Populate this slice with items. */
  def populate(itemFunc: () => Option[Item]) = {
    val n: Option[Item] = itemFunc()
    n.foreach { i =>
      this.addItem(i)
    }
  }
  
  /* Add an item to a random position to this slice. */
  def addItem(i: Item) = {
    i.positionX = 160 + rand.nextInt(704)
    i.positionY = 32 + rand.nextInt(64)
    this.items += i
  }
}
