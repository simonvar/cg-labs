package io.github.simonvar.cglabs.core3d

import scalafx.scene.paint.Color

class Line(val a: Point, val b: Point, val color: Color = Color.Black) extends Rotatable[Line]
    with Scalable[Line] {

  override def rotateX(alpha: Double): Line = new Line(a.rotateX(alpha), b.rotateX(alpha), color)

  override def rotateY(alpha: Double): Line = new Line(a.rotateY(alpha), b.rotateY(alpha), color)

  override def rotateZ(alpha: Double): Line = new Line(a.rotateZ(alpha), b.rotateZ(alpha), color)

  override def scale(alpha: Double): Line = new Line(a.scale(alpha), b.scale(alpha), color)
}