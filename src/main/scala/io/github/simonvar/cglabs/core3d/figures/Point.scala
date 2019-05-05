package io.github.simonvar.cglabs.core3d.figures

import java.lang.Math._

import io.github.simonvar.cglabs.core3d.Grid
import io.github.simonvar.cglabs.core3d.base.Graphical
import scalafx.scene.paint.Color

class Point(val x: Double, val y: Double, val z: Double, val color: Color = Color.Black) extends Graphical {

  override def rotateX(alpha: Double): Point =
    new Point(x, y * cos(alpha.toRadians) - z * sin(alpha.toRadians), z * cos(alpha.toRadians) + y * sin(alpha.toRadians))

  override def rotateY(alpha: Double): Point =
    new Point(x * cos(alpha.toRadians) + z * sin(alpha.toRadians), y, z * cos(alpha.toRadians) - x * sin(alpha.toRadians))

  override def rotateZ(alpha: Double): Point =
    new Point(x * cos(alpha.toRadians) - y * sin(alpha.toRadians), x * sin(alpha.toRadians) + y * cos(alpha.toRadians), z)

  override def scale(alpha: Double): Point =
    new Point(x * alpha, y * alpha, z * alpha)

  override def moveX(alpha: Double): Point =
    new Point(x + alpha, y, z)

  override def moveY(alpha: Double): Point =
    new Point(x, y + alpha, z)

  override def moveZ(alpha: Double): Point =
    new Point(x, y, z + alpha)

  override def draw(grid: Grid): Unit = {}

}
