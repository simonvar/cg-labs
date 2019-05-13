package io.github.simonvar.cglabs.core3d.figures

import java.lang.Math._

import io.github.simonvar.cglabs.core3d.Context
import io.github.simonvar.cglabs.core3d.base.Graphical
import scalafx.scene.paint.Color

class Point(override val tag: String, val x: Double, val y: Double, val z: Double, val color: Color = Color.Black) extends Graphical {

  override def rotateX(alpha: Double): Point =
    copy(y = y * cos(alpha.toRadians) - z * sin(alpha.toRadians), z = z * cos(alpha.toRadians) + y * sin(alpha.toRadians))

  override def rotateY(alpha: Double): Point =
    copy(x = x * cos(alpha.toRadians) + z * sin(alpha.toRadians), z = z * cos(alpha.toRadians) - x * sin(alpha.toRadians))

  override def rotateZ(alpha: Double): Point =
    copy(x = x * cos(alpha.toRadians) - y * sin(alpha.toRadians), y = x * sin(alpha.toRadians) + y * cos(alpha.toRadians))

  override def scale(alpha: Double): Point =
    copy(x = x * alpha, y = y * alpha, z = z * alpha)

  override def moveX(alpha: Double): Point =
    copy(x = x + alpha)

  override def moveY(alpha: Double): Point =
    copy(y = y + alpha)

  override def moveZ(alpha: Double): Point =
    copy(z = z + alpha)

  override def draw(grid: Context): Unit = {}

  private def copy(tag: String = tag, x: Double = x, y: Double = y, z: Double = z, color: Color = color) =
    new Point(tag, x, y, z, color)

}
