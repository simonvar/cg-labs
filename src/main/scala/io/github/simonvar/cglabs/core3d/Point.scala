package io.github.simonvar.cglabs.core3d

import Math._

class Point(val x: Double, val y: Double, val z: Double) extends Rotatable[Point] with Scalable[Point] {

  def +(p: Point): Point = new Point(x + p.x, y + p.y, z + p.z)

  def *(a: Double) = new Point(x * a, y * a, z * a)

  override def rotateX(alpha: Double): Point =
    new Point(x, y * cos(alpha.toRadians) - z * sin(alpha.toRadians), z * cos(alpha.toRadians) + y * sin(alpha.toRadians))

  override def rotateY(alpha: Double): Point =
    new Point(x * cos(alpha.toRadians) + z * sin(alpha.toRadians), y, z * cos(alpha.toRadians) - x * sin(alpha.toRadians))

  override def rotateZ(alpha: Double): Point =
    new Point(x * cos(alpha.toRadians) - y * sin(alpha.toRadians), x * sin(alpha.toRadians) + y * cos(alpha.toRadians), z)

  override def scale(alpha: Double): Point =
    new Point(x * alpha, y * alpha, z * alpha)
}
